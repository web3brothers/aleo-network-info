package tech.web3brothers.aleonetworkstate.services.collectors;

import tech.web3brothers.aleonetworkstate.dtos.NodeInfoDto;
import tech.web3brothers.aleonetworkstate.dtos.PeersDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.arteam.simplejsonrpc.client.JsonRpcClient;
import com.github.arteam.simplejsonrpc.client.Transport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class AleoNodeRpcRequestor implements NodeRequestor {

    private final JsonRpcClient client;
    private final String url;

    public AleoNodeRpcRequestor(String ip, ObjectMapper objectMapper) {
        this.url = String.format("http://%s:3030", ip);
        this.client = new JsonRpcClient(new Transport() {

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(3000)
                    .setSocketTimeout(3000)
                    .build();

            CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setDefaultRequestConfig(requestConfig)
                    .setConnectionTimeToLive(20, TimeUnit.SECONDS)
                    .build();

            @NotNull
            @Override
            public String pass(@NotNull String request) throws IOException {
                HttpPost post = new HttpPost(url);
                post.setEntity(new StringEntity(request, Charsets.UTF_8));
                post.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8.toString());
                try (CloseableHttpResponse httpResponse = httpClient.execute(post)) {
                    return EntityUtils.toString(httpResponse.getEntity(), Charsets.UTF_8);
                }
            }
        }, objectMapper);
    }

    @Override
    public Set<String> getPeers() {
        Optional<PeersDto> peersinfo = makeRequest(PeersDto.class, "getpeerinfo");

        if (peersinfo.isEmpty()) {
            return new HashSet<>();
        }
        return peersinfo.get().getPeers().stream()
                .map(ditryIp -> ditryIp.split(":")[0])
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<NodeInfoDto> getNodeInfo() {
        return makeRequest(NodeInfoDto.class, "getnodeinfo");
    }

    @Override
    public Optional<Double> getBlockCount() {
        return makeRequest(Double.class, "getblockcount");
    }

    private <T> Optional<T> makeRequest(Class<T> clazz, String methodName) {
        try {
            return Optional.of(client.createRequest()
                    .method(methodName)
                    .id("documentation")
                    .returnAs(clazz)
                    .execute());
        } catch (Exception e) {
            log.warn("Some error occurred on rpc {}, {}", methodName, e.getMessage());
            return Optional.empty();
        }
    }
}

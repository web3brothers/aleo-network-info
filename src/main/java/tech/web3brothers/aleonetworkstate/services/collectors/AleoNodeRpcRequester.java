package tech.web3brothers.aleonetworkstate.services.collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.arteam.simplejsonrpc.client.JsonRpcClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import tech.web3brothers.aleonetworkstate.dtos.NodeInfoDto;
import tech.web3brothers.aleonetworkstate.dtos.NodeStatDto;
import tech.web3brothers.aleonetworkstate.dtos.PeersDto;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class AleoNodeRpcRequester implements NodeRequester, AutoCloseable {

    private final JsonRpcClient client;
    private final String url;
    private final CloseableHttpClient httpClient;

    public AleoNodeRpcRequester(String ip, ObjectMapper objectMapper) {
        this.url = String.format("http://%s:3030", ip);

        int timeoutSeconds = 5;
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeoutSeconds * 1000)
                .setConnectTimeout(timeoutSeconds * 1000)
                .setSocketTimeout(timeoutSeconds * 1000)
                .build();

        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionTimeToLive(20, TimeUnit.SECONDS)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(1, false))
                .build();

        this.client = new JsonRpcClient(request -> {
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(request, StandardCharsets.UTF_8));
            post.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            try (CloseableHttpResponse httpResponse = httpClient.execute(post)) {
                return EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
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

    public Optional<NodeStatDto> getNodeStat() { return makeRequest(NodeStatDto.class, "getnodestats");}

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

    @Override
    public void close() throws Exception {
        httpClient.close();
    }
}

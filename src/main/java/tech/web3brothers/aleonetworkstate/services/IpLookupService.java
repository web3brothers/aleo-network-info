package tech.web3brothers.aleonetworkstate.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import tech.web3brothers.aleonetworkstate.dtos.IpInfoDto;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static tech.web3brothers.aleonetworkstate.utils.IpOrgUtils.normalizeOrganizationName;

@Slf4j
@Service
public class IpLookupService {

    private WebClient webclient;
    private final ObjectMapper objectMapper;

    public IpLookupService(ObjectMapper objectMapper,
                           @Value("${ip.lookup-service.url}") String url) {
        this.objectMapper = objectMapper;

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn -> {
                    conn.addHandler(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                });

        webclient = WebClient.builder()
                .baseUrl(url)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    public List<IpInfoDto> lookUp(List<String> ips) {
        try {
            IpInfoDto[] response = webclient.post()
                    .uri("/batch")
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(objectMapper.writeValueAsString(ips))
                    .retrieve()
                    .bodyToMono(IpInfoDto[].class)
                    .log().block();

            if (response == null) {
                log.error("Not able to get response for lookup of ips {}", ips);
                return List.of();
            }
            return normalizeData(List.of(response));

        } catch (JsonProcessingException e) {
            log.error("Error while converting value to json", e);
        }
        return List.of();
    }

    private List<IpInfoDto> normalizeData(List<IpInfoDto> original) {
        original.forEach(i -> i.setOrg(normalizeOrganizationName(i.getOrg(), i.getIsp())));
        return original;
    }
}

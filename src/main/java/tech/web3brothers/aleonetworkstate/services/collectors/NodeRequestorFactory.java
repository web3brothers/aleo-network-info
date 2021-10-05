package tech.web3brothers.aleonetworkstate.services.collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NodeRequestorFactory {

    private final ObjectMapper objectMapper;

    public NodeRequestor createNodeRequestor(String ip) {
        return new AleoNodeRpcRequestor(ip, objectMapper);
    }
}

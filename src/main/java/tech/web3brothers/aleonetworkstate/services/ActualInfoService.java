package tech.web3brothers.aleonetworkstate.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import tech.web3brothers.aleonetworkstate.dtos.CurrentNodeDto;
import tech.web3brothers.aleonetworkstate.services.collectors.AleoNodeRpcRequester;

@Service
@RequiredArgsConstructor
public class ActualInfoService {

    private final BootNodeService bootNodeService;
    private final ObjectMapper objectMapper;

    public CurrentNodeDto getActualNodeInfo(String ip) throws Exception {
        CurrentNodeDto dto = CurrentNodeDto.builder().ip(ip).build();
        try (AleoNodeRpcRequester requester = new AleoNodeRpcRequester(ip, objectMapper)) {

            Pair<String, Double> actualBlockHeight = getActualBlockHeight();

            dto.setNodeInfo(requester.getNodeInfo().orElse(null));
            dto.setNodeStat(requester.getNodeStat().orElse(null));
            dto.setBlockCount(requester.getBlockCount().orElse(null));
            dto.setBootNodeBlockCount(actualBlockHeight.getValue());
            dto.setBootNodeIp(actualBlockHeight.getKey());
        }
        return dto;
    }

    private Pair<String, Double> getActualBlockHeight() throws Exception {
        String ipOfSyncedBootNode = bootNodeService.getIpOfSyncedBootnode();
        if (ipOfSyncedBootNode == null) {
            return null;
        }
        try (AleoNodeRpcRequester requester = new AleoNodeRpcRequester(ipOfSyncedBootNode, objectMapper)) {
            return Pair.of(ipOfSyncedBootNode, requester.getBlockCount().orElse(null));
        }
    }

}

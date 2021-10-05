package tech.web3brothers.aleonetworkstate.events;

import lombok.Builder;
import lombok.Data;
import tech.web3brothers.aleonetworkstate.dtos.NodeInfoDto;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Builder
public class NodeInformationCollectedEvent {
    private OffsetDateTime collectedOn;
    private NodeInfoDto nodeInfo;
    private Double blockCount;
    private String ip;
    private boolean notReachable;
}

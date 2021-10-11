package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class CurrentNodeDto {
    private String ip;
    private Double blockCount;
    private String bootNodeIp;
    private Double bootNodeBlockCount;
    private NodeStatDto nodeStat;
    private NodeInfoDto nodeInfo;
}

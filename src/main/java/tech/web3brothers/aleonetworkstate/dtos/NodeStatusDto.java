package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;

@ToString
@Builder
@Data
public class NodeStatusDto {
    private OffsetDateTime launched;
    private String ip;
    private Boolean syncing;
    private Double blockCount;
    private String country;
    private String version;
    private String org;
}

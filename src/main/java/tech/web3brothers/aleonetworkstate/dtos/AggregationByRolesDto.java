package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class AggregationByRolesDto {
    private Integer totalNodes;
    private Integer miners;
    private Integer fullNodes;
    private Integer bootNodes;
    private Integer notReachable;
}

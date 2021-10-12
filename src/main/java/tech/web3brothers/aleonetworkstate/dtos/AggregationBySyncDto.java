package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AggregationBySyncDto {
    private String synced;
    private Integer nodes;
}

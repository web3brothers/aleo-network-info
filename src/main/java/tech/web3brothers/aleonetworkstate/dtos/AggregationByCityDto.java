package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AggregationByCityDto {
    private String city;
    private String org;
    private Double lat;
    private Double lon;
    private Integer nodes;
}

package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Builder
@Data
public class AggregationByAppVersionsDto {
    private List<Version> versions;

    @Data
    public static class Version {
        private String version;
        private Integer nodes;
    }
}

package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Builder
@ToString
@Data
public class AggregationByProvidersDto {
    private List<Provider> providers;

    @Builder
    @Data
    public static class Provider {
        private String org;
        private BigDecimal percents;
        private Integer nodes;
    }

    @Data
    public static class ProviderWithNodesNumber {
        private String org;
        private Integer nodes;
    }
}

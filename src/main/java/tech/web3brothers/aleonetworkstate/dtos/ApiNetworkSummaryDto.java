package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.List;

@ToString
@Builder
@Data
public class ApiNetworkSummaryDto {
    private OffsetDateTime lastCollectedOn;
    private List<AggregationByCityDto> aggregationByCities;
    private AggregationByRolesDto aggregationByRoles;
    private AggregationByProvidersDto aggregationByProviders;
    private AggregationByAppVersionsDto aggregationByVersions;
}

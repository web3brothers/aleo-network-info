package tech.web3brothers.aleonetworkstate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.web3brothers.aleonetworkstate.dao.NodeStatisticsDao;
import tech.web3brothers.aleonetworkstate.dtos.ApiNetworkSummaryDto;
import tech.web3brothers.aleonetworkstate.dtos.NodeStatusDto;
import tech.web3brothers.aleonetworkstate.dtos.NodesType;
import tech.web3brothers.aleonetworkstate.dtos.Page;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class NetworkSummaryService {

    private final NodeStatisticsDao nodeStatisticsDao;

    public ApiNetworkSummaryDto getSummaryForLatestCollectionData(NodesType nodesType) {
        OffsetDateTime lastCollectingDate = nodeStatisticsDao.getLastCollectingDate();
        return ApiNetworkSummaryDto.builder()
                .aggregationByCities(nodeStatisticsDao.getNodesCountAggregatedByCities(lastCollectingDate, nodesType))
                .aggregationByRoles(nodeStatisticsDao.getNodesCountByDifferentRoles(lastCollectingDate))
                .aggregationByProviders(nodeStatisticsDao.getNodesCountAggregatedByProviders(lastCollectingDate, nodesType))
                .aggregationByVersions(nodeStatisticsDao.getNodesCountAggregatedByVersions(lastCollectingDate, nodesType))
                .lastCollectedOn(lastCollectingDate)
                .build();
    }

    public Page<NodeStatusDto> getListOfNodesForLatestCollectionData(NodesType nodesType, String searchTerm, Integer offset, Integer limit) {
        OffsetDateTime lastCollectingDate = nodeStatisticsDao.getLastCollectingDate();
        return nodeStatisticsDao.getNodesList(nodesType, searchTerm, lastCollectingDate, offset, limit);
    }
}

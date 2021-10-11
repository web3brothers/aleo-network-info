package tech.web3brothers.aleonetworkstate.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.web3brothers.aleonetworkstate.dtos.ApiNetworkSummaryDto;
import tech.web3brothers.aleonetworkstate.dtos.NodeStatusDto;
import tech.web3brothers.aleonetworkstate.dtos.NodesType;
import tech.web3brothers.aleonetworkstate.dtos.Page;
import tech.web3brothers.aleonetworkstate.services.NetworkSummaryService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class NetworkSummaryController {

    private final NetworkSummaryService networkSummaryService;

    @GetMapping(Api.Public.NODES_SUMMARY)
    public ApiNetworkSummaryDto getNetworkSummary(@RequestParam(value = "nodes_type", defaultValue = "ALL")
                                                          NodesType nodesType) {
        return networkSummaryService.getSummaryForLatestCollectionData(nodesType);
    }

    @GetMapping(Api.Public.NODES)
    public Page<NodeStatusDto> getNodes(@RequestParam(value = "nodes_type", defaultValue = "ALL") NodesType nodesType,
                                        @RequestParam(value = "searchTerm", required = false) String searchTerm,
                                        @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                        @RequestParam(value = "limit", defaultValue = "25") Integer limit) {
        return networkSummaryService.getListOfNodesForLatestCollectionData(nodesType, searchTerm, offset, limit);
    }

}

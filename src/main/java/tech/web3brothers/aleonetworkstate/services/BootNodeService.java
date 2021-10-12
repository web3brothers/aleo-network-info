package tech.web3brothers.aleonetworkstate.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.web3brothers.aleonetworkstate.dao.NodeStatisticsDao;
import tech.web3brothers.aleonetworkstate.dtos.NodeStatusDto;
import tech.web3brothers.aleonetworkstate.dtos.NodesType;
import tech.web3brothers.aleonetworkstate.dtos.Page;
import tech.web3brothers.aleonetworkstate.services.cache.CacheNames;
import tech.web3brothers.aleonetworkstate.services.collectors.AleoNodeRpcRequester;
import tech.web3brothers.aleonetworkstate.utils.StringHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BootNodeService {

    private final String bootnodes;
    private final ObjectMapper objectMapper;
    private final NodeStatisticsDao nodeStatisticsDao;

    public BootNodeService(@Value("${bootnodes}") String bootnodes,
                           ObjectMapper objectMapper, NodeStatisticsDao nodeStatisticsDao) {
        this.bootnodes = bootnodes;
        this.objectMapper = objectMapper;
        this.nodeStatisticsDao = nodeStatisticsDao;
    }

    /**
     * Get IP address of first synced bootnode, from bootnodes param.
     *
     * @return ip address or null if there are no syncted bootstrap node.
     */
    @Cacheable(CacheNames.GET_IP_OF_SYNCED_BOOTNODE)
    public String getIpOfSyncedBootnode() {
        List<String> bootnodesFromConfig = StringHelper.splitString(this.bootnodes);
        Page<NodeStatusDto> nodesList = nodeStatisticsDao.getNodesList(NodesType.BOOT_NODES, null,
                nodeStatisticsDao.getLastCollectingDate(), 0, 100);
        Set<String> bootnodesFromDB = nodesList.getItems().stream().map(NodeStatusDto::getIp)
                .collect(Collectors.toSet());
        Set<String> bootnodes = new HashSet<>(bootnodesFromConfig);
        bootnodes.addAll(bootnodesFromDB);

        Map<String, Double> syncedBootnodesWithBlockHeight = new HashMap<>();
        for (String bootnode : bootnodes) {
            AleoNodeRpcRequester requestor = new AleoNodeRpcRequester(bootnode, objectMapper);
            if (requestor.getNodeInfo().isPresent() &&
                    requestor.getNodeInfo().get().getSyncing()) {
                Double blockHeight = requestor.getBlockCount().orElse(null);
                if(blockHeight != null){
                    syncedBootnodesWithBlockHeight.put(bootnode, blockHeight);
                }
            }
        }

        if(syncedBootnodesWithBlockHeight.isEmpty()){
            return null;
        }

        String bootnode = syncedBootnodesWithBlockHeight.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        log.debug("IP of synced bootnode: {}",  bootnode);
        return bootnode;
    }
}

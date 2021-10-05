package tech.web3brothers.aleonetworkstate.services.collectors;

import tech.web3brothers.aleonetworkstate.dtos.NodeInfoDto;

import java.util.Optional;
import java.util.Set;

public interface NodeRequestor {
    Set<String> getPeers();

    Optional<NodeInfoDto> getNodeInfo();

    Optional<Double> getBlockCount();
}

package tech.web3brothers.aleonetworkstate.services.collectors;

import tech.web3brothers.aleonetworkstate.dtos.NodeInfoDto;
import tech.web3brothers.aleonetworkstate.dtos.NodeStatDto;

import java.util.Optional;
import java.util.Set;

public interface NodeRequester extends AutoCloseable {
    Set<String> getPeers();

    Optional<NodeInfoDto> getNodeInfo();

    Optional<NodeStatDto> getNodeStat();

    Optional<Double> getBlockCount();
}

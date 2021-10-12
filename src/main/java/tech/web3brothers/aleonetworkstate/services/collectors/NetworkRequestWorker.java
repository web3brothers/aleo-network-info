package tech.web3brothers.aleonetworkstate.services.collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import tech.web3brothers.aleonetworkstate.dtos.NodeInfoDto;
import tech.web3brothers.aleonetworkstate.events.NodeInformationCollectedEvent;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

@Slf4j
@RequiredArgsConstructor
public class NetworkRequestWorker implements Runnable {

    private final String ip;
    private final BlockingQueue<String> queueToAddPeersTo;
    private final ApplicationEventPublisher publisher;
    private final OffsetDateTime collectedOn;
    private final NodeRequestorFactory nodeRequestorFactory;

    @Override
    public void run() {
        log.debug("Start processing {}", ip);
        NodeInformationCollectedEvent event = NodeInformationCollectedEvent.builder().build();
        try(NodeRequester req = nodeRequestorFactory.createNodeRequestor(ip)){
            Set<String> peers = req.getPeers();

            event.setCollectedOn(collectedOn);
            event.setIp(ip);

            if (peers.isEmpty()) {
                prepareNotReachableEvent(event);
                publisher.publishEvent(event);
                return;
            }
            queueToAddPeersTo.addAll(peers);

            Optional<NodeInfoDto> nodeInfo = req.getNodeInfo();

            if (nodeInfo.isEmpty()) {
                prepareNotReachableEvent(event);
                publisher.publishEvent(event);
                return;
            }
            event.setNodeInfo(nodeInfo.get());
            event.setBlockCount(req.getBlockCount().orElse(null));
            publisher.publishEvent(event);
            log.debug("Processed: {}", event);
        } catch (Exception e) {
            log.warn("Exception occurred whe tried to close resource {}", e.getMessage());
        }
    }

    private void prepareNotReachableEvent(NodeInformationCollectedEvent event) {
        event.setNotReachable(true);
        event.setNodeInfo(NodeInfoDto.builder().build());
    }
}

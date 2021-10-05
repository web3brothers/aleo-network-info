package tech.web3brothers.aleonetworkstate.listeners;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tech.web3brothers.aleonetworkstate.dao.NodeStatisticsDao;
import tech.web3brothers.aleonetworkstate.events.NodeInformationCollectedEvent;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class NodeInformationCollectedEventListener {

    private final NodeStatisticsDao nodeStatisticsDao;

    @EventListener
    public void handleContextStart(NodeInformationCollectedEvent event) {
        log.info("Handle event {}", event);
        nodeStatisticsDao.insertNodeStatistics(event);
    }
}

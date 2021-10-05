package tech.web3brothers.aleonetworkstate.services.collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.web3brothers.aleonetworkstate.events.CollectingFinishedEvent;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service

public class RequestWorkerCoordinator {

    private final LinkedBlockingQueue<String> toProcessQueue = new LinkedBlockingQueue<>(10000);

    private final TaskExecutor taskExecutor;
    private final ApplicationEventPublisher publisher;
    private final String bootNodes;
    private final NodeRequestorFactory nodeRequestorFactory;

    public RequestWorkerCoordinator(@Qualifier("threadPoolTaskExecutor") TaskExecutor taskExecutor,
                                    ObjectMapper mapper,
                                    ApplicationEventPublisher publisher,
                                    NodeRequestorFactory nodeRequestorFactory,
                                    @Value("${bootnodes}") String bootNodes) {
        this.taskExecutor = taskExecutor;
        this.publisher = publisher;
        this.bootNodes = bootNodes;
        this.nodeRequestorFactory = nodeRequestorFactory;
    }

    @Scheduled(cron = "${gathering_schedule}")
    public void startGatherInformation() throws InterruptedException {
        crawl(bootNodes);
    }

    private void crawl(String nodes) throws InterruptedException {
        toProcessQueue.addAll(Arrays.asList(nodes.split(",")));

        final Set<String> processed = new HashSet<>();
        final OffsetDateTime collectedOn = Instant.now().atOffset(ZoneOffset.UTC);

        final AtomicInteger activeThreadsCounter = new AtomicInteger(0);
        boolean examineFirstNode = true;

        while (true) {

            if (toProcessQueue.isEmpty() && activeThreadsCounter.get() > 0) {
                Thread.sleep(3000);
            }

            if (toProcessQueue.isEmpty()) {
                break;
            }

            String nodeIpToProcess = toProcessQueue.take();
            log.debug("Take from queue {}", nodeIpToProcess);
            log.debug("Queue size {} / nodes {}", toProcessQueue.size(), toProcessQueue);

            if (processed.contains(nodeIpToProcess)) {
                log.debug("{} already processed", nodeIpToProcess);
                continue;
            }

            taskExecutor.execute(new NetworkRequestWorker(nodeIpToProcess, toProcessQueue, publisher,
                    collectedOn, activeThreadsCounter, nodeRequestorFactory));
            processed.add(nodeIpToProcess);

            if (examineFirstNode) {
                Thread.sleep(2000);
                examineFirstNode = false;
            }
        }

        log.debug("Processing completed {}", collectedOn);
        publisher.publishEvent(CollectingFinishedEvent.builder()
                .collectedOn(collectedOn)
                .build());
    }
}

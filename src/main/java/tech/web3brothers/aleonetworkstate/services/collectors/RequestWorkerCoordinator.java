package tech.web3brothers.aleonetworkstate.services.collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.web3brothers.aleonetworkstate.events.CollectingFinishedEvent;
import tech.web3brothers.aleonetworkstate.utils.StringHelper;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@Slf4j
@Service
public class RequestWorkerCoordinator {

    private final LinkedBlockingQueue<String> toProcessQueue = new LinkedBlockingQueue<>(1000000);

    private final ApplicationEventPublisher publisher;
    private final String bootNodes;
    private final NodeRequestorFactory nodeRequestorFactory;
    private final ExecutorService executorService;

    public RequestWorkerCoordinator(ApplicationEventPublisher publisher,
                                    NodeRequestorFactory nodeRequestorFactory,
                                    @Value("${bootnodes}") String bootNodes) {

        this.publisher = publisher;
        this.bootNodes = bootNodes;
        this.nodeRequestorFactory = nodeRequestorFactory;
        executorService = Executors.newFixedThreadPool(10);
    }

    @Scheduled(cron = "${gathering_schedule}")
    public void startGatherInformation() throws InterruptedException {
        crawl(bootNodes);
    }

    private void crawl(String nodes) {
        toProcessQueue.addAll(StringHelper.splitString(nodes));

        final Set<String> processed = new HashSet<>();
        final OffsetDateTime collectedOn = Instant.now().atOffset(ZoneOffset.UTC);

        final List<Future> futures = new ArrayList<>();
        try {
            while (true) {

                log.debug("Queue size {} / Futures done|canceled|ongoing {}|{}|{} ",
                        toProcessQueue.size(),
                        numOfDoneTasks(futures),
                        numOfCanceledTasks(futures),
                        numOfInProgressTasks(futures));

                if (toProcessQueue.isEmpty()
                        && futures.stream().allMatch(f -> f.isDone() || f.isCancelled())) {
                    break;
                }

                String nodeIpToProcess = toProcessQueue.poll(30, TimeUnit.SECONDS);
                log.debug("Take from queue {}", nodeIpToProcess);

                if (nodeIpToProcess == null) {
                    break;
                }

                if (processed.contains(nodeIpToProcess)) {
                    log.debug("{} already processed", nodeIpToProcess);
                    continue;
                }

                futures.add(executorService.submit(new NetworkRequestWorker(nodeIpToProcess, toProcessQueue, publisher,
                        collectedOn, nodeRequestorFactory)));
                processed.add(nodeIpToProcess);
            }

        } catch (InterruptedException ex) {
            log.debug("No more ips in queue, queue size {}", toProcessQueue.size());
        }

        log.debug("Processing completed {}", collectedOn);
        publisher.publishEvent(CollectingFinishedEvent.builder()
                .collectedOn(collectedOn)
                .build());
    }

    private long numOfCanceledTasks(List<Future> futures) {
        return numOfFutures(futures, Future::isCancelled);
    }

    private long numOfDoneTasks(List<Future> futures) {
        return numOfFutures(futures, Future::isDone);
    }

    private long numOfInProgressTasks(List<Future> futures) {
        return numOfFutures(futures, f -> !f.isDone() && !f.isCancelled());
    }

    private long numOfFutures(List<Future> futures, Predicate<Future> predicate) {
        return futures.stream().filter(predicate).count();
    }
}

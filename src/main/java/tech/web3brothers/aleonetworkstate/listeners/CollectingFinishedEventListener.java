package tech.web3brothers.aleonetworkstate.listeners;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tech.web3brothers.aleonetworkstate.services.IpLookupService;
import tech.web3brothers.aleonetworkstate.dao.IpInfoDao;
import tech.web3brothers.aleonetworkstate.dao.NodeStatisticsDao;
import tech.web3brothers.aleonetworkstate.dtos.IpInfoDto;
import tech.web3brothers.aleonetworkstate.events.CollectingFinishedEvent;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CollectingFinishedEventListener {

    private final NodeStatisticsDao nodeStatisticsDao;
    private final IpInfoDao ipInfoDao;
    private final IpLookupService ipLookupService;

    @EventListener
    public void handleContextStart(CollectingFinishedEvent event) throws InterruptedException {
        log.debug("Collecting finished event {}", event);

        List<String> nodesWithoutIpInformation = nodeStatisticsDao.getNodesWithoutIpInformation(
                event.getCollectedOn());

        List<List<String>> partitions = ListUtils.partition(nodesWithoutIpInformation, 100);
        for (List<String> partition : partitions) {
            //TODO replace with normal solution: https://stackoverflow.com/questions/65258130/data-structure-to-manage-a-maximum-number-of-requests-per-minute-for-an-api
            List<IpInfoDto> ipInfoDtos = ipLookupService.lookUp(partition);
            log.debug("Inserting {}", ipInfoDtos);
            //TODO replace with batch insert
            ipInfoDtos.forEach(ipInfoDao::insertIpInfo);
            Thread.sleep(4000);
        }

        nodeStatisticsDao.updateActualInformationOnCollectingFinished(event);

        log.info("Finished!");

    }
}

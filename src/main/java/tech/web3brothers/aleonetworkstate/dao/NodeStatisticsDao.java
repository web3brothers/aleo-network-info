package tech.web3brothers.aleonetworkstate.dao;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import tech.web3brothers.aleonetworkstate.dtos.AggregationByAppVersionsDto;
import tech.web3brothers.aleonetworkstate.dtos.AggregationByCityDto;
import tech.web3brothers.aleonetworkstate.dtos.AggregationByProvidersDto;
import tech.web3brothers.aleonetworkstate.dtos.AggregationByRolesDto;
import tech.web3brothers.aleonetworkstate.dtos.NodeStatusDto;
import tech.web3brothers.aleonetworkstate.dtos.NodesType;
import tech.web3brothers.aleonetworkstate.dtos.Page;
import tech.web3brothers.aleonetworkstate.events.CollectingFinishedEvent;
import tech.web3brothers.aleonetworkstate.events.NodeInformationCollectedEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import static generated.jooq.model.Tables.COLLECTION_DATES;
import static generated.jooq.model.Tables.IP_INFO;
import static generated.jooq.model.Tables.NODES_STATISTICS;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.DSL.selectCount;


@Repository
@RequiredArgsConstructor
public class NodeStatisticsDao {

    private final DSLContext dslContext;

    public void insertNodeStatistics(NodeInformationCollectedEvent event) {
        dslContext.insertInto(NODES_STATISTICS,
                NODES_STATISTICS.IP,
                NODES_STATISTICS.INFO_COLLECTED_ON,
                NODES_STATISTICS.BOOT_NODE,
                NODES_STATISTICS.MINER,
                NODES_STATISTICS.SYNCING,
                NODES_STATISTICS.LAUNCHED,
                NODES_STATISTICS.VERSION,
                NODES_STATISTICS.BLOCK_COUNT,
                NODES_STATISTICS.NOT_REACHABLE)
                .values(event.getIp(),
                        event.getCollectedOn().toInstant().atOffset(ZoneOffset.UTC),
                        event.getNodeInfo().getBootNode(),
                        event.getNodeInfo().getMiner(),
                        event.getNodeInfo().getSyncing(),
                        event.getNodeInfo().getLaunched() != null ? event.getNodeInfo().getLaunched().toInstant().atOffset(ZoneOffset.UTC) : null,
                        event.getNodeInfo().getVersion(),
                        event.getBlockCount(),
                        event.isNotReachable())
                .execute();
    }

    public OffsetDateTime getLastCollectingDate() {
        List<OffsetDateTime> offsetDateTimes = dslContext.select(COLLECTION_DATES.COLLECTION_DATE)
                .from(COLLECTION_DATES)
                .orderBy(COLLECTION_DATES.COLLECTION_DATE.desc())
                .limit(1)
                .fetchInto(OffsetDateTime.class);
        return offsetDateTimes.isEmpty() ? null : offsetDateTimes.get(0);
    }

    public @NotNull AggregationByRolesDto getNodesCountByDifferentRoles(OffsetDateTime statisticsCollectionDate) {
        return dslContext.select(
                field(selectCount().from(NODES_STATISTICS).where(NODES_STATISTICS.INFO_COLLECTED_ON.eq(statisticsCollectionDate)))
                        .as("totalHosts"),
                field(selectCount().from(NODES_STATISTICS).where(NODES_STATISTICS.INFO_COLLECTED_ON.eq(statisticsCollectionDate))
                        .and(NODES_STATISTICS.MINER.eq(true)))
                        .as("miners"),
                field(selectCount().from(NODES_STATISTICS).where(NODES_STATISTICS.INFO_COLLECTED_ON.eq(statisticsCollectionDate))
                        .and(NODES_STATISTICS.MINER.eq(false)
                                .and(NODES_STATISTICS.BOOT_NODE.eq(false)
                                        .and(NODES_STATISTICS.NOT_REACHABLE.eq(false))))).as("fullNodes"),
                field(selectCount().from(NODES_STATISTICS).where(NODES_STATISTICS.INFO_COLLECTED_ON.eq(statisticsCollectionDate))
                        .and(NODES_STATISTICS.BOOT_NODE.eq(true))).as("bootNodes"),
                field(selectCount().from(NODES_STATISTICS).where(NODES_STATISTICS.INFO_COLLECTED_ON.eq(statisticsCollectionDate))
                        .and(NODES_STATISTICS.NOT_REACHABLE.eq(true))).as("notReachable"))
                .fetchSingleInto(AggregationByRolesDto.class);
    }

    public @NotNull List<AggregationByCityDto> getNodesCountAggregatedByCities(OffsetDateTime statisticsCollectionDate, NodesType nodesType) {
        return dslContext
                .select(IP_INFO.CITY, IP_INFO.ORG, IP_INFO.LAT, IP_INFO.LON, DSL.count(IP_INFO.IP).as("nodes"))
                .from(NODES_STATISTICS)
                .leftJoin(IP_INFO)
                .on(NODES_STATISTICS.IP.eq(IP_INFO.IP))
                .where(prepareNodesTypeAndCollectionDateConditions(statisticsCollectionDate, nodesType))
                .groupBy(IP_INFO.CITY, IP_INFO.ORG, IP_INFO.LAT, IP_INFO.LON)
                .orderBy(DSL.count(IP_INFO.IP).desc())
                .fetchInto(AggregationByCityDto.class);
    }

    public AggregationByProvidersDto getNodesCountAggregatedByProviders(OffsetDateTime statisticsCollectionDate, NodesType nodesType) {
        final Integer numOfTopProviders = 8;

        List<AggregationByProvidersDto.ProviderWithNodesNumber> providers = dslContext
                .select(IP_INFO.ORG, DSL.count(IP_INFO.IP).as("nodes"))
                .from(NODES_STATISTICS)
                .leftJoin(IP_INFO)
                .on(NODES_STATISTICS.IP.eq(IP_INFO.IP))
                .where(prepareNodesTypeAndCollectionDateConditions(statisticsCollectionDate, nodesType))
                .groupBy(IP_INFO.ORG)
                .orderBy(DSL.count(IP_INFO.IP).desc())
                .fetchInto(AggregationByProvidersDto.ProviderWithNodesNumber.class);

        Integer totalNodes = providers.stream().map(p -> p.getNodes()).reduce(0, (acc, x) -> acc + x);
        List<AggregationByProvidersDto.ProviderWithNodesNumber> topProviders = providers.stream().limit(numOfTopProviders).collect(Collectors.toList());

        List<AggregationByProvidersDto.Provider> result = topProviders.stream()
                .map(tp -> AggregationByProvidersDto.Provider.builder()
                        .org(tp.getOrg())
                        .percents(BigDecimal.valueOf((tp.getNodes() * 1.0 / totalNodes) * 100)
                                .setScale(2, RoundingMode.HALF_UP))
                        .nodes(tp.getNodes())
                        .build())
                .collect(Collectors.toList());

        BigDecimal restPercents = result.stream().map(AggregationByProvidersDto.Provider::getPercents).reduce(BigDecimal.valueOf(100),
                (acc, x) -> acc.subtract(x).setScale(2, RoundingMode.HALF_UP));
        Integer restNodesNumber = result.stream().map(AggregationByProvidersDto.Provider::getNodes).reduce(totalNodes,
                (acc, x) -> acc - x);

        result.add(AggregationByProvidersDto.Provider.builder()
                .nodes(restNodesNumber)
                .percents(restPercents)
                .org("Others")
                .build());

        return AggregationByProvidersDto.builder()
                .providers(result)
                .build();
    }

    public AggregationByAppVersionsDto getNodesCountAggregatedByVersions(OffsetDateTime statisticsCollectionDate, NodesType nodesType) {
        List<AggregationByAppVersionsDto.Version> versions = dslContext.select(
                NODES_STATISTICS.VERSION,
                DSL.count(NODES_STATISTICS.IP).as("nodes"))
                .from(NODES_STATISTICS)
                .where(prepareNodesTypeAndCollectionDateConditions(statisticsCollectionDate, nodesType)
                        .and(NODES_STATISTICS.VERSION.isNotNull()))
                .groupBy(NODES_STATISTICS.VERSION)
                .orderBy(NODES_STATISTICS.VERSION.desc())
                .fetchInto(AggregationByAppVersionsDto.Version.class);
        return AggregationByAppVersionsDto
                .builder()
                .versions(versions)
                .build();
    }

    public List<String> getNodesWithoutIpInformation(OffsetDateTime lastCollectingDate) {
        if (lastCollectingDate == null) {
            return List.of();
        }
        return dslContext.select(NODES_STATISTICS.IP)
                .from(NODES_STATISTICS)
                .leftJoin(IP_INFO)
                .on(NODES_STATISTICS.IP.eq(IP_INFO.IP))
                .where(IP_INFO.IP.isNull())
                .and(NODES_STATISTICS.INFO_COLLECTED_ON.eq(lastCollectingDate))
                .fetchInto(String.class);
    }

    public void updateActualInformationOnCollectingFinished(CollectingFinishedEvent event) {
        dslContext.insertInto(COLLECTION_DATES, COLLECTION_DATES.COLLECTION_DATE)
                .values(event.getCollectedOn())
                .execute();
    }

    public Page<NodeStatusDto> getNodesList(NodesType nodesType, String searchTerm, OffsetDateTime lastCollectingDate, Integer offset, Integer limit) {
        Condition conditions = NODES_STATISTICS.NOT_REACHABLE.isFalse()
                .and(prepareNodesTypeAndCollectionDateConditions(lastCollectingDate, nodesType));

        if (StringUtils.isNotBlank(searchTerm)) {
            conditions.and(NODES_STATISTICS.IP.like(searchTerm));
        }

        List<NodeStatusDto> items = dslContext.select(NODES_STATISTICS.LAUNCHED,
                NODES_STATISTICS.IP,
                NODES_STATISTICS.SYNCING,
                NODES_STATISTICS.BLOCK_COUNT,
                IP_INFO.COUNTRY,
                NODES_STATISTICS.VERSION,
                IP_INFO.ORG)
                .from(NODES_STATISTICS)
                .leftJoin(IP_INFO)
                .on(NODES_STATISTICS.IP.eq(IP_INFO.IP))
                .where(conditions)
                .orderBy(NODES_STATISTICS.SYNCING.asc().nullsLast(), NODES_STATISTICS.BLOCK_COUNT.desc())
                .limit(limit)
                .offset(offset)
                .fetchInto(NodeStatusDto.class);

        int total = dslContext.fetchCount(select().from(NODES_STATISTICS).where(conditions));

        return new Page<>(items, offset, total);
    }

    private Condition prepareNodesTypeAndCollectionDateConditions(OffsetDateTime statisticsCollectionDate, NodesType nodesType) {
        Condition whereConditions = DSL.and(NODES_STATISTICS.INFO_COLLECTED_ON.eq(statisticsCollectionDate));
        switch (nodesType) {
            case ALL:
                break;
            case MINERS:
                whereConditions = whereConditions.and(NODES_STATISTICS.MINER.isTrue());
                break;
            case BOOT_NODES:
                whereConditions = whereConditions.and(NODES_STATISTICS.BOOT_NODE.isTrue());
                break;
            case FULL_NODES:
                whereConditions = whereConditions.and(NODES_STATISTICS.MINER.isFalse()
                        .and(NODES_STATISTICS.BOOT_NODE.isFalse())
                        .and(NODES_STATISTICS.NOT_REACHABLE.isFalse()));
        }
        return whereConditions;
    }
}

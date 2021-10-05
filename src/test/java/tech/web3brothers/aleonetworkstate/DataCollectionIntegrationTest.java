package tech.web3brothers.aleonetworkstate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.web3brothers.aleonetworkstate.dao.NodeStatisticsDao;
import tech.web3brothers.aleonetworkstate.dtos.IpInfoDto;
import tech.web3brothers.aleonetworkstate.dtos.NodeInfoDto;
import tech.web3brothers.aleonetworkstate.dtos.NodeStatusDto;
import tech.web3brothers.aleonetworkstate.dtos.NodesType;
import tech.web3brothers.aleonetworkstate.dtos.Page;
import tech.web3brothers.aleonetworkstate.services.collectors.NodeRequestor;
import tech.web3brothers.aleonetworkstate.services.collectors.NodeRequestorFactory;
import tech.web3brothers.aleonetworkstate.services.collectors.RequestWorkerCoordinator;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;


class DataCollectionIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private RequestWorkerCoordinator requestWorkerCoordinator;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NodeStatisticsDao nodeStatisticsDao;

    @MockBean
    private NodeRequestorFactory nodeRequestorFactory;

    public static MockWebServer mockBackEnd;

    private static String node1Ip = "1.1.1.1";
    private static String node2Ip = "2.2.2.2";
    private static String node3Ip = "3.3.3.3";

    @BeforeAll
    public static void setUp() throws IOException {
        System.setProperty("bootnodes", String.format("%s,%s", node1Ip, node2Ip));
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
        System.setProperty("ip.lookup-service.url",
                String.format("http://localhost:%s", mockBackEnd.getPort()));
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void testCollectingInfoFromThreeNodes() throws InterruptedException, JsonProcessingException {
        // arrange
        Map<String, NodeInfoDto> nodesInfo = initializeNodesMocksAndGetExpectedData();
        Map<String, IpInfoDto> ipsInfo = initializeIpLookUpMock();

        // act
        requestWorkerCoordinator.startGatherInformation();

        // assert
        Awaitility.await().until(() -> nodeStatisticsDao.getLastCollectingDate() != null);

        Page<NodeStatusDto> nodesList = nodeStatisticsDao.getNodesList(NodesType.ALL, null,
                nodeStatisticsDao.getLastCollectingDate(), 0, 100);

        assertThat(nodesList.getItems().size()).isEqualTo(nodesInfo.size());
        nodesList.getItems().forEach(node -> {
            NodeInfoDto expectedNodeInfo = nodesInfo.get(node.getIp());
            IpInfoDto expectedIpInfo = ipsInfo.get(node.getIp());
            assertThat(node.getBlockCount()).isNotNull();
            assertThat(node.getLaunched()).isEqualTo(expectedNodeInfo.getLaunched().toInstant()
                    .atOffset(ZoneOffset.UTC));
            assertThat(node.getVersion()).isEqualTo(expectedNodeInfo.getVersion());
            assertThat(node.getSyncing()).isEqualTo(expectedNodeInfo.getSyncing());
            assertThat(node.getOrg()).isEqualTo(expectedIpInfo.getOrg());
            assertThat(node.getCountry()).isEqualTo(expectedIpInfo.getCountry());
        });

    }

    private Map<String, IpInfoDto> initializeIpLookUpMock() throws JsonProcessingException {
        Map<String, IpInfoDto> ipsInfo = Map.of(DataCollectionIntegrationTest.node1Ip, IpInfoDto.builder()
                        .as("TestTelecom1").city("Dnepr").country("Ukraine").countryCode("380").isp("TestIsp").org("TestOrg1")
                        .lon(0.1111).lat(2.2222).query(node1Ip).region("DneprRegion").status("active").zip("49087")
                        .build(),
                node2Ip, IpInfoDto.builder()
                        .as("TestTelecom2").city("Kiev").country("Ukraine").countryCode("380").isp("TestIsp2").org("TestOrg1")
                        .lon(0.1112).lat(2.2223).query(node2Ip).region("KievRegion").status("active").zip("01087")
                        .build(),
                node3Ip, IpInfoDto.builder()
                        .as("TestTelecom3").city("Kiev").country("Ukraine").countryCode("380").isp("TestIsp2").org("TestOrg1")
                        .lon(0.1114).lat(2.2224).query(node3Ip).region("KievRegion").status("active").zip("01087")
                        .build()
        );

        mockBackEnd.enqueue(new MockResponse()
                .setBody(objectMapper.writeValueAsString(ipsInfo.values()))
                .addHeader("Content-Type", "application/json"));

        return ipsInfo;
    }

    private Map<String, NodeInfoDto> initializeNodesMocksAndGetExpectedData() {
        Map<String, NodeInfoDto> nodesInfo = new HashMap<>();

        nodesInfo.put(node1Ip, NodeInfoDto.builder()
                .bootNode(true)
                .launched(Date.from(Instant.parse("2007-12-03T10:15:30.00Z")))
                .miner(false)
                .syncing(true)
                .version("1.1")
                .build());

        nodesInfo.put(node2Ip, NodeInfoDto.builder()
                .bootNode(false)
                .launched(Date.from(Instant.parse("2008-12-03T10:15:30.00Z")))
                .miner(false)
                .syncing(true)
                .version("1.2")
                .build());

        nodesInfo.put(node3Ip, NodeInfoDto.builder()
                .bootNode(false)
                .launched(Date.from(Instant.parse("2009-12-03T10:15:30.00Z")))
                .miner(true)
                .syncing(false)
                .version("1.3")
                .build());

        NodeRequestor requestor1 = mock(NodeRequestor.class);
        NodeRequestor requestor2 = mock(NodeRequestor.class);
        NodeRequestor requestor3 = mock(NodeRequestor.class);

        Mockito.when(nodeRequestorFactory.createNodeRequestor(node1Ip))
                .thenReturn(requestor1);
        Mockito.when(nodeRequestorFactory.createNodeRequestor(node2Ip))
                .thenReturn(requestor2);
        Mockito.when(nodeRequestorFactory.createNodeRequestor(node3Ip))
                .thenReturn(requestor3);

        Mockito.when(requestor1.getPeers()).thenReturn(Set.of(node1Ip, node2Ip, node3Ip));
        Mockito.when(requestor2.getPeers()).thenReturn(Set.of(node1Ip));
        Mockito.when(requestor3.getPeers()).thenReturn(Set.of(node2Ip));

        Mockito.when(requestor1.getBlockCount()).thenReturn(Optional.of(1.0));
        Mockito.when(requestor2.getBlockCount()).thenReturn(Optional.of(2.0));
        Mockito.when(requestor3.getBlockCount()).thenReturn(Optional.of(3.0));

        Mockito.when(requestor1.getNodeInfo()).thenReturn(Optional.of(nodesInfo.get(node1Ip)));
        Mockito.when(requestor2.getNodeInfo()).thenReturn(Optional.of(nodesInfo.get(node2Ip)));
        Mockito.when(requestor3.getNodeInfo()).thenReturn(Optional.of(nodesInfo.get(node3Ip)));

        return nodesInfo;
    }

}

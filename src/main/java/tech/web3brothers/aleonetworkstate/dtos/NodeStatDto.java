package tech.web3brothers.aleonetworkstate.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeStatDto {

    @JsonAlias("connections")
    private ConnectionsDto connections;

    @Data
    public static class ConnectionsDto {
        @JsonAlias("all_accepted")
        private Double allAccepted;
        @JsonAlias("all_initiated")
        private Double allInitiated;
        @JsonAlias("all_rejected")
        private Double allRejected;
        @JsonAlias("average_duration")
        private Integer averageDuration;
        @JsonAlias("connected_peers")
        private Double connectedPeers;
        @JsonAlias("connecting_peers")
        private Double connectingPeers;
        @JsonAlias("disconnected_peers")
        private Double disconnectedPeers;
    }

}

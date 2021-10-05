package tech.web3brothers.aleonetworkstate.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NodeInfoDto {
    @JsonAlias("is_bootnode")
    private Boolean bootNode;
    @JsonAlias("is_miner")
    private Boolean miner;
    @JsonAlias("is_syncing")
    private Boolean syncing;
    private Date launched;
    private String version;
}

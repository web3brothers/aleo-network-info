package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class PeersDto {
    private Set<String> peers;
}

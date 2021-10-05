package tech.web3brothers.aleonetworkstate.events;

import lombok.Builder;
import lombok.Data;
import tech.web3brothers.aleonetworkstate.dtos.NodeInfoDto;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Builder
public class CollectingFinishedEvent {
    private OffsetDateTime collectedOn;
}

package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Page<T> {
    private final List<T> items;
    private final int offset;
    private final int total;
    private final OffsetDateTime lastCollectedOn;
}

package tech.web3brothers.aleonetworkstate.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Page<T> {
    private final List<T> items;
    private final int offset;
    private final int total;
}

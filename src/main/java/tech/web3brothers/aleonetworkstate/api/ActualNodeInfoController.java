package tech.web3brothers.aleonetworkstate.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.web3brothers.aleonetworkstate.dtos.CurrentNodeDto;
import tech.web3brothers.aleonetworkstate.services.ActualInfoService;

@RestController
@RequiredArgsConstructor
public class ActualNodeInfoController {

    private final ActualInfoService actualInfoService;

    @GetMapping(Api.Public.ACTUAL_NODE_INFO)
    public CurrentNodeDto getNodeActualInfo(@PathVariable String ip) throws Exception {
        return actualInfoService.getActualNodeInfo(ip);
    }
}

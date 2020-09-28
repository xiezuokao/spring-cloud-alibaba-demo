package com.hecai.api.mq.feign;

import com.hecai.common.config.restdata.RestData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "slipp-microservice-mq")
@RequestMapping("/seata")
public interface SeataFeign {
    @GetMapping("/test")
    RestData test();
}

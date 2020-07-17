package com.zx.hystris.webpi.feign;

import com.zx.hystris.webpi.fallback.TestServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhouxuan
 * @date 2020/7/16 9:37
 */
@FeignClient(name = "hystris-service", fallback = TestServiceFallBack.class)
public interface TestServiceClient {

    @GetMapping("test")
    String test(@RequestParam("str") String str);
}

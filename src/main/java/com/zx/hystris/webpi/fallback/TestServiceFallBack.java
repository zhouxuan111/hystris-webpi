package com.zx.hystris.webpi.fallback;

import com.zx.hystris.webpi.feign.TestServiceClient;
import org.springframework.stereotype.Component;

/**
 * @author zhouxuan
 * @date 2020/7/16 9:40
 */
@Component
public class TestServiceFallBack implements TestServiceClient {

    @Override
    public String test(String str) {
        return "test方法调用失败，触发熔断降级";
    }
}

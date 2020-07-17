package com.zx.hystris.webpi.controller;

import com.zx.hystris.webpi.feign.TestServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouxuan
 * @date 2020/7/16 9:48
 */
@RestController
public class TestController {

    @Autowired
    private TestServiceClient testServiceClient;

    @GetMapping("test")
    public String test() {
        String result = testServiceClient.test("test");
        return result;
    }
}

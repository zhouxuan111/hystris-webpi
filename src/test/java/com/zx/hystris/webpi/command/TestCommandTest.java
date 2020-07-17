package com.zx.hystris.webpi.command;

import com.zx.hystris.webpi.feign.TestServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhouxuan
 * @date 2020/7/16 16:51
 */
@SpringBootTest
public class TestCommandTest {

    @Autowired
    TestServiceClient testServiceClient;

    @Test
    public void test() {
        Integer result = new TestCommand(testServiceClient).execute();
        System.out.println(result);
    }
}

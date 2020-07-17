package com.zx.hystris.webpi.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.zx.hystris.webpi.feign.TestServiceClient;

/**
 * @author zhouxuan
 * @date 2020/7/16 16:26
 */
public class TestCommand extends HystrixCommand<Integer> {

    private TestServiceClient TestServiceClient;

    protected TestCommand(TestServiceClient TestServiceClient) {
        //hystrixCommandGroupKey是指类名 HystrixCommandKey指类中的方法名
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("test"))
                //默认属性
                .andCommandPropertiesDefaults(
                        //至少有10个请求，熔断器才进行错误率计算
                        HystrixCommandProperties.Setter().withCircuitBreakerRequestVolumeThreshold(10)
                                //熔断器中断请求5秒后进入半打开状态，放部分流量进行重试
                                .withCircuitBreakerSleepWindowInMilliseconds(5000)
                                //错误率达到50%开启熔断器
                                .withCircuitBreakerErrorThresholdPercentage(50).withCircuitBreakerEnabled(true))
                //设置线程池的属性
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(10)));
        this.TestServiceClient = TestServiceClient;
    }

    @Override
    protected Integer run() throws Exception {
        System.out.println(TestServiceClient.test("test"));
        return 1;
    }

    /**
     * 降级方法的实现
     * @return
     */
    @Override
    protected Integer getFallback() {
        return -1;
    }
}

package com.qilin.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {


    // 重试机制
    @Bean
    public Retryer myRetryer() {
      //  return Retryer.NEVER_RETRY;
        // 初次间隔 最大间隔 最大请求次数(1+2) = 3
        return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(1L), 3);
    }


    // 日志记录级别
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


}

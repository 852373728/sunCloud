package com.qilin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.qilin.mapper")
@Slf4j
@EnableDiscoveryClient
@RefreshScope
public class CloudProviderPayment {
    public static void main(String[] args) {
        SpringApplication.run(CloudProviderPayment.class,args);
        log.info("=========启动成功=========");
    }

}
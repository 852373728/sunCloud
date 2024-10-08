package com.qilin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient

public class CloudAlibabaProviderPayment {

    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaProviderPayment.class,args);
        System.out.println("==================================");
        System.out.println("=============启动成功===============");
        System.out.println("==================================");
    }
}
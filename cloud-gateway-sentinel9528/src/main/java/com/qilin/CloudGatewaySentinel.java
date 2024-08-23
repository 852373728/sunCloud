package com.qilin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudGatewaySentinel {
    public static void main(String[] args) {
        SpringApplication.run(CloudGatewaySentinel.class, args);
        System.out.println("==================================");
        System.out.println("=============启动成功===============");
        System.out.println("==================================");
    }
}
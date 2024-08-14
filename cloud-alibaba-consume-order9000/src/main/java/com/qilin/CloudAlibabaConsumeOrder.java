package com.qilin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudAlibabaConsumeOrder {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaConsumeOrder.class,args);
        System.out.println("==================================");
        System.out.println("=============启动成功===============");
        System.out.println("==================================");
    }
}
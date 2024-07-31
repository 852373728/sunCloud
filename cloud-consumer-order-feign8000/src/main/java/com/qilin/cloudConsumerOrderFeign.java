package com.qilin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients
public class cloudConsumerOrderFeign {
    public static void main(String[] args) {
        SpringApplication.run(cloudConsumerOrderFeign.class,args);

        log.info("========================");
        log.info("=========启动成功=========");
        log.info("========================");
    }
}
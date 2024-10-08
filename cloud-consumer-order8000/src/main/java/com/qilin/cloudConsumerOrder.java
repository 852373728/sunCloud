package com.qilin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class cloudConsumerOrder {
    public static void main(String[] args) {
        SpringApplication.run(cloudConsumerOrder.class,args);

        log.info("=========启动成功=========");
    }
}
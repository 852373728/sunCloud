package com.qilin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.qilin.mapper")
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients
public class CloudSeataOrder {
    public static void main(String[] args) {
        SpringApplication.run(CloudSeataOrder.class,args);
        log.info("=========启动成功=========");

    }


}
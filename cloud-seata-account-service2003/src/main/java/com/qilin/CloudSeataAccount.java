package com.qilin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.qilin.mapper")
@Slf4j
@EnableDiscoveryClient
public class CloudSeataAccount {
    public static void main(String[] args) {
        SpringApplication.run(CloudSeataAccount.class,args);
        log.info("=========启动成功=========");

    }

}
package com.qilin.controller;

import cn.hutool.core.util.IdUtil;
import com.qilin.entities.PayDTO;
import com.qilin.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RefreshScope
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${qilin.config}")
    private String configStr;

    @GetMapping("/pay/nacos/{id}")
    public String getPayInfo(@PathVariable("id") Integer id) {
        return "nacos registry serverPost: " + serverPort + ", id: " + id;
    }


    @GetMapping("/pay/nacos/config")
    public String config() {
        return this.configStr;
    }

    // openfeign和sentinel
//    @GetMapping("/pay/nacos/get/{orderNo}")
//    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
//    public Result<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
//        // 模拟查询
//        PayDTO payDTO = new PayDTO(1024, orderNo, "pay" + IdUtil.simpleUUID(), 1, BigDecimal.valueOf(9.9));
//        return Result.success(payDTO);
//    }
//
//    public Result<PayDTO> handlerBlockHandler(String orderNo, BlockException e) {
//        return Result.fail("服务提供者" + e.getMessage());
//    }
}

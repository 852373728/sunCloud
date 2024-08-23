package com.qilin.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.qilin.entities.PayDTO;
import com.qilin.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

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
    @SentinelResource()
    public String config() {
        return this.configStr;
    }


    @GetMapping("/pay/nacos/testabd")
    public String testabd(@RequestParam(value = "userid",required = false)String userid,
                          @RequestParam(value = "order",required = false)String order) {
        System.out.println(userid);
        System.out.println(order);
        return "/pay/nacos/testabd";
    }

    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    public Result<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        System.out.println(orderNo);
        // 模拟查询
        PayDTO payDTO = new PayDTO(1024, orderNo, "pay" + IdUtil.simpleUUID(), 1, BigDecimal.valueOf(9.9),new Date());
        return Result.success(payDTO);
    }

    public Result<PayDTO> handlerBlockHandler(@PathVariable("orderNo") String orderNo, BlockException e) {
        System.out.println("线程拥堵");
        return Result.fail("线程拥堵：" + e.getMessage());
    }
}

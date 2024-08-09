package com.qilin.controller;

import com.qilin.apis.PayFeignApi;
import com.qilin.entities.PayDTO;
import com.qilin.util.Result;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/gateway")
public class OrderGatewayController {
    @Resource
    private PayFeignApi payFeignApi;


    @GetMapping("/pay/get/{id}")
    public Result<PayDTO> getPayById(@PathVariable("id") Integer id,@RequestParam(value = "levelname",required = false) String levelname) {
        return payFeignApi.getByIdGateway(id,levelname);
    }

    @GetMapping("/pay/getInfo")
    @CircuitBreaker(name = "cloud-gateway", fallbackMethod = "fallback4CircuitBreaker")
    public Result<String> getInfo() {
        return payFeignApi.getInfoGateway();
    }

    public Result<String> fallback4CircuitBreaker(Throwable throwable) {
        throwable.printStackTrace();
        return Result.fail("系统繁忙, 请稍后重试...");
    }

}

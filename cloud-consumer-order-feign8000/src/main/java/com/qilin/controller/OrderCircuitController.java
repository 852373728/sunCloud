package com.qilin.controller;

import com.qilin.apis.PayFeignApi;
import com.qilin.util.Result;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/feign")
public class OrderCircuitController {

    @Resource
    private PayFeignApi payFeignApi;


    @GetMapping("/pay/circuit/get/{id}")
    @CircuitBreaker(name = "cloud-provider-payment", fallbackMethod = "fallback4CircuitBreaker")
    public Result<String> getPayById4CircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }
    public Result<String> fallback4CircuitBreaker(Throwable throwable) {
        return Result.fail("系统繁忙, 请稍后重试...");
    }

    @GetMapping("/pay/semaphore/get/{id}")
    @Bulkhead(name = "cloud-provider-payment", fallbackMethod = "fallback4BulkheadThreadPool", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<Result<String>> fallback4BulkheadThreadPool(@PathVariable("id") Integer id) {
        System.out.println(Thread.currentThread().getName() + " into ...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " over ...");
        //return payFeignApi.mySemaphore(id);
        return CompletableFuture.supplyAsync(() -> payFeignApi.mySemaphore(id));
    }

//    public Result<String> getPayById4Bulkhead(Exception e) {
//        return Result.fail("超出最大请求数量限制, 请稍后重试...");
//    }

    public CompletableFuture<Result<String>> fallback4BulkheadThreadPool(Integer id,Throwable throwable) {
        return CompletableFuture.supplyAsync(() -> Result.fail("超出最大请求数量限制, 请稍后重试..."));
    }

    @GetMapping("/pay/rateLimit/get/{id}")
    @RateLimiter(name = "cloud-provider-payment", fallbackMethod = "getPayById4RateLimit")
    public Result<String> getPayById4RateLimit(@PathVariable("id") Integer id) {
        return payFeignApi.mySemaphore(id);
    }




    public Result<String> getPayById4RateLimit(Integer id,Throwable throwable) {
        return Result.success("服务器限流, 请稍后重试...");
    }
}

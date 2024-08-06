package com.qilin.apis;

import com.qilin.entities.PayDTO;
import com.qilin.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "cloud-provider-payment")
public interface PayFeignApi {

    @PostMapping("/pay/add")
    Result<Integer> addPay(@RequestBody PayDTO pay);

    @DeleteMapping("/pay/del/{id}")
    Result<Integer> deletePay(@PathVariable("id") Integer id);

    @PutMapping("/pay/update")
    Result<Integer> updatePay(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/get/{id}")
    Result<PayDTO> getById(@PathVariable("id") Integer id);

    @GetMapping("/pay/getAll")
    Result<List<PayDTO>> getAll();

    @GetMapping("/pay/getInfo")
    Result<String> getInfo();

    @GetMapping("/pay/circuit/{id}")
    Result<String> myCircuit(@PathVariable("id") Integer id);

    @GetMapping("/pay/semaphore/{id}")
    Result<String> mySemaphore(@PathVariable("id") Integer id);

    @GetMapping("/pay/rateLimit/{id}")
    Result<String> myRateLimit(@PathVariable("id") Integer id);

    @GetMapping("/payMicrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);


    @GetMapping("/pay/gateway/get/{id}")
    Result<PayDTO> getById4Gateway(@PathVariable("id") Integer id);

    @GetMapping("/pay/gateway/getInfo")
    Result<String> getInfo4Gateway();

}

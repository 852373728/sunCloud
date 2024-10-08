package com.qilin.controller;

import com.qilin.apis.PayFeignApi;
import com.qilin.entities.PayDTO;
import com.qilin.util.Result;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.StringJoiner;

@RestController
@RequestMapping("/feign")
public class OrderController {
    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/pay/add")
    public Result<Integer> addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @DeleteMapping("/pay/del/{id}")
    public Result<Integer> delOrder(@PathVariable("id") Integer id) {
        return payFeignApi.deletePay(id);
    }

    @PutMapping("/pay/update")
    public Result<Integer> updateOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.updatePay(payDTO);
    }

    @GetMapping("/pay/get/{id}")
    public Result<PayDTO> getPayById(@PathVariable("id") Integer id) {
        Result<PayDTO> pay=null;
        long l = System.currentTimeMillis();
        try {

            pay = payFeignApi.getById(id);
        }catch (Exception e){
            e.printStackTrace();
            long l1 = System.currentTimeMillis();
            System.out.println((l1-l)/1000);
        }

        return pay;
    }

    @GetMapping("/pay/getAll")
    public Result<List<PayDTO>> getAll() {
        return payFeignApi.getAll();
    }

    @GetMapping("/pay/getInfo")
    public Result<String> getInfo() {
        return payFeignApi.getInfo();
    }

    @GetMapping("/discovery")
    public String discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        System.out.println("=================");
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-provider-payment");
        for (ServiceInstance instance : instances) {
            StringJoiner joiner = new StringJoiner("\t");
            joiner.add(instance.getServiceId());
            joiner.add(instance.getHost());
            joiner.add(instance.getPort() + "");
            joiner.add(instance.getUri() + "");
            System.out.println(joiner);
        }
        return instances.getFirst().getServiceId() + ":" + instances.getFirst().getPort();
    }
}

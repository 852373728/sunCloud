package com.qilin.apis;

import com.qilin.entities.PayDTO;
import com.qilin.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-pay-provider",fallback = NacosPayFeignApiFallBack.class)
public interface NacosPayFeignApi {

    @GetMapping("/pay/nacos/get/{orderNo}")
    Result<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}

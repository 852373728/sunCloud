package com.qilin.apis;

import com.qilin.entities.PayDTO;
import com.qilin.util.Result;
import org.springframework.stereotype.Component;

@Component
public class NacosPayFeignApiFallBack implements NacosPayFeignApi{
    @Override
    public Result<PayDTO> getPayByOrderNo(String orderNo) {
        return Result.fail("异常返回，服务不可用");
    }
}

package com.qilin.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payMicrometer")
public class PayMicrometerController {

    @GetMapping("/{id}")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return "这是链路追踪, Id:" + IdUtil.simpleUUID();
    }
}

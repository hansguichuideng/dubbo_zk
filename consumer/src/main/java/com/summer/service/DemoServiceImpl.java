package com.summer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl {


    @Reference
    private DemoService demoService;


    public String test() {
        return demoService.hello("summer");
    }
}

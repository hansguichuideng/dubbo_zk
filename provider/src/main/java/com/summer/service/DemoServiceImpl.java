package com.summer.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String hello(String name) {
        return "hello word: " + name;
    }
}

package com.summer.controller;

import com.summer.service.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DemoServiceImpl demoServiceImpl;


    @RequestMapping("test")
    public String test() {

        return demoServiceImpl.test();
    }

}

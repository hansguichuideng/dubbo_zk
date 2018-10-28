package com.summer;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath:spring-*1.xml")
@DubboComponentScan(basePackages = "com.summer")
public class ConsumerStart {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerStart.class, args);
    }
}

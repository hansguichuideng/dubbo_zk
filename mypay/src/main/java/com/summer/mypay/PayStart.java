package com.summer.mypay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ImportResource(locations = {"classpath:spring*.xml"})
@EnableScheduling
public class PayStart {
    public static void main(String[] args) {
        SpringApplication.run(PayStart.class, args);
    }
}


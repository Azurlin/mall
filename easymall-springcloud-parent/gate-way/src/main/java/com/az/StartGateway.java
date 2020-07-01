package com.az;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy//开启zuul的代理,目的就是引入zuul中的过滤逻辑
public class StartGateway {
    public static void main(String[] args) {
        SpringApplication.run(StartGateway.class,args);
    }
}

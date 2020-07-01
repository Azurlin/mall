package com.az;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.az.cart.mapper")
public class StartCart {
    public static void main(String[] args) {
        SpringApplication.run(StartCart.class,args);
    }
    //创建一个RestTemplate对象
    @Bean
    @LoadBalanced
    public RestTemplate init(){
        return new RestTemplate();
    }
}

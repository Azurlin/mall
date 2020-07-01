package com.az;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class StartRibbon {
    public static void main(String[] args) {
        SpringApplication.run(StartRibbon.class,args);
    }
    /*利用ribbon组件,创建一个容器RESTTEMPLATE对象,并且添加注解
     * 使得这个对象的所有调用方法都经过ribbon负载均衡计算
     * */
    @Bean
    @LoadBalanced//ribbon组件会在这个对象上做一个记号,一旦这个对象发起http请求
    //经过ribbon负载均衡计算的拦截
    public RestTemplate init(){
        return new RestTemplate();
    }
}
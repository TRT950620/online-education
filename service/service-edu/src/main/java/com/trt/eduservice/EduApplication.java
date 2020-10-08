package com.trt.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@MapperScan("com.trt.eduservice.mapper")可写在启动类，更建议写在配置类
@ComponentScan(basePackages = {"com.trt"}) //加上此注解才能访问service-base的swagger
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}

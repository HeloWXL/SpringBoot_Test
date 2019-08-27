package com.test.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 //加上注解@EnableSwagger2 表示开启Swagger
@SpringBootApplication
@MapperScan("com.test.demo.mapper")
public class DemoApplication {
    protected static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    public static void main(String[] args) {
        logger.info("SpringBoot开始加载");
        SpringApplication.run(DemoApplication.class, args);
        logger.info("SpringBoot加载完毕");
    }
}

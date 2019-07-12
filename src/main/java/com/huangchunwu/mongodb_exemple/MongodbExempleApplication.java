package com.huangchunwu.mongodb_exemple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableWebMvc
public class MongodbExempleApplication {



    public static void main(String[] args) {
        SpringApplication.run(MongodbExempleApplication.class, args);

    }

}

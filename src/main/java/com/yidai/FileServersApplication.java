package com.yidai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yidai.dao")
public class FileServersApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileServersApplication.class, args);
    }

}

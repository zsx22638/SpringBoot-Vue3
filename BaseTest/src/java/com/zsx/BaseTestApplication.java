package com.zsx;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zsx.mapper")
@SpringBootApplication
public class BaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseTestApplication.class, args);
    }
}


package com.example.tap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TapApplication {

    public static void main(String[] args) {
        SpringApplication.run(TapApplication.class, args);
    }

}

package com.cherepnin.restful_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "RESTful API", version = "1.0"))
public class ResTfulApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResTfulApiApplication.class, args);
    }

}

package com.github.michalbladowski.springdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Swagger3Config {

    @Bean
    public OpenAPI customOpenAPI(@Value("${spring.application.version:unknown}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Batch and JMS demo application + REST API")
                        .description("RESTful API for Spring Batch and JMS demo application")
                        .version(appVersion)
                        .license(new License().name("(C) Michal Bladowski"))
                );
    }
}

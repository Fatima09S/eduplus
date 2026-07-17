package com.ipd.eduplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI eduPlusOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EduPlus API")
                        .version("1.0")
                        .description("API REST de gestion scolaire pour les étudiants, enseignants, cours et uploads"));
    }
}

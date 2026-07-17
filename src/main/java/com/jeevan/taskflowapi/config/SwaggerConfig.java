package com.jeevan.taskflowapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()

                .servers(List.of(
                        new Server()
                                .url("https://task-flow-api-production-9465.up.railway.app")
                ))
                  .info(new Info()cd
                        .title("Task Flow API")
                        .version("1.0")
                        .description("Task Management REST API"))

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(SCHEME_NAME)
                )

                .components(
                        new Components()
                                .addSecuritySchemes(
                                        SCHEME_NAME,
                                        new SecurityScheme()
                                                .name(SCHEME_NAME)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );

    }

}
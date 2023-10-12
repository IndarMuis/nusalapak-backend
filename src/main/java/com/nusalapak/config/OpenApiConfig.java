package com.nusalapak.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Scope;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "nusalapak",
                        email = "nusalapak.imgroup@gmail.com",
                        url = "https://nusalapak.com"
                ),
                description = "OpenAPI Documentation For Nusalapak Backend Service",
                title = "OpenAPI Spesification - ImGroup",
                version = "1.0",
                license = @License(
                        name = "ImGroup License",
                        url = "https://im.group.com"
                )
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Production ENV",
                        url = "https://nusalapak.com"
                )
        }
//        security = @SecurityRequirement(
//                name = "BearerAuth"
//        )
)
@SecurityScheme(
        name = "BearerAuth",
        description = "JWT Authentication Token",
        scheme = "Bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}

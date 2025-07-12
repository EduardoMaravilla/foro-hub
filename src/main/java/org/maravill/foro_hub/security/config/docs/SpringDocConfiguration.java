package org.maravill.foro_hub.security.config.docs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringDocConfiguration {

    private static final String SECURITY_SCHEME_NAME = "security-token";

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(apiInfo())
                .addServersItem(new Server()
                        .description("Development")
                        .url("http://localhost:9095/api/v1"))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, createSecurityScheme()));
    }

    private Info apiInfo() {
        return new Info()
                .title("Foro Hub API")
                .version("v1.0.0")
                .description("""
                Esta API REST forma parte del backend de **Foro Hub**, una plataforma de discusión y aprendizaje.
                Fue desarrollada como parte del **Challenge de Alura Latam - Oracle Next Education**.
                
                Incluye funcionalidades para la gestión de usuarios, roles, módulos, tópicos y respuestas,
                con autenticación basada en JWT y control de acceso.
                """)
                .contact(new Contact()
                        .name("Eduardo Maravilla")
                        .email("eduardomaravilladev@hotmail.com")
                        .url("https://eduardo-maravilla.netlify.app/"))
                .license(new License()
                        .name("MIT License")
                        .url("https://github.com/EduardoMaravilla/foro-hub/blob/master/LICENSE"));
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWE")
                .in(SecurityScheme.In.HEADER)
                .description("Token de acceso para autenticación y autorización");
    }
}

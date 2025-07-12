package org.maravill.foro_hub.security.config.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenApi(){

        Contact contact = new Contact();
        contact.name("Eduardo Maravilla");
        contact.email("eduardomaravilladev@hotmail.com");

        License license = new License();
        license.setName("MIT LICENSE");


        Info info = new Info();
        info.setTitle("FORO HUB");
        info.setVersion("V-1.0.0");
        info.setDescription("Documentación de la API del foro hub");
        info.setContact(contact);
        info.setLicense(license);

        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setType(SecurityScheme.Type.HTTP);
        securityScheme.scheme("bearer");
        securityScheme.bearerFormat("JWT");


     return new OpenAPI()
             .info(info);
    }
}

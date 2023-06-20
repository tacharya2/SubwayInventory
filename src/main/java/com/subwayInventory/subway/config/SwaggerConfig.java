package com.subwayInventory.subway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Demo-Project",
                description = "This API is used to do CRUD operation of Products",
                version = "v1",
                contact = @Contact(name = "Tek Acharya", email = "sarmakash430@gmail.com")
        )
)
public class SwaggerConfig {

}
// http://localhost:8083/swagger-ui/index.html

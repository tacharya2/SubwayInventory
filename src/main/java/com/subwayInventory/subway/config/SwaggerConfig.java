package com.subwayInventory.subway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Product API",
                description = "This API is used to do CRUD operation of Products",
                version = "v1",
                contact = @Contact(name = "Tek Acharya", email = "sarmakash430@gmail.com")
        )
)
public class SwaggerConfig {

}
//Swagger: http://localhost:8083/swagger-ui/index.html
//Actuator Health: http://localhost:8083/actuator/health


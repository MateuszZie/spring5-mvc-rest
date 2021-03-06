package org.mateuszziebura.spring5mvcrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .tags(new Tag("Customer","it's customer"),new Tag("Vendor","Vendors"))
                .apiInfo(metaData());
    }
    private ApiInfo metaData(){

//        Contact contact = new Contact("Mateusz Ziebura", null,
//                "john@springfrmework.guru");

        return new ApiInfo(
                "Mateusz Ziebura",
                "Spring 5 MVC REST",
                "1.0",
                "Terms of Service: blah",
                null,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }

}

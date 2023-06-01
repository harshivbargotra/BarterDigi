package com.stackroute.searchservice.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SearchServiceSwaggerConfiguration {
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo("Search Service", "BarterDigi SearchService", "1.0", "http://localhost:9988/searchService"
                        , new Contact("Manan Pandya", "url", ""), "opensource", "", Collections.emptyList()));
    }

}

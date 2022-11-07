/**
 * <p>
 * This is the base package for all the configuration classes
 * which is for Swagger and Web security
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * This Swagger Config class is a configuration class and this
 * class is used to document process of the project
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * <p>
     * This method is used to generate the REST API documents for restful web services.
     * It provides a user interface to access our restful web services via the web browser.
     * </p>
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
package com.ideas2it.healthCare.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {
    @Bean
    public ModelMapper addModelMapper() {
        return new ModelMapper();
    }
}

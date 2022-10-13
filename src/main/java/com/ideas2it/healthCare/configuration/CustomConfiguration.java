package com.ideas2it.healthCare.configuration;

import com.ideas2it.healthCare.dto.FeedbackDto;
import com.ideas2it.healthCare.model.Feedback;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {
    @Bean
    public ModelMapper addModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Feedback, FeedbackDto>() {
            @Override
            protected void configure() {
                skip(destination.getDoctor().getFeedbacks());
                skip(destination.getPatient().getFeedback());}
        });
        return modelMapper;
    }
}

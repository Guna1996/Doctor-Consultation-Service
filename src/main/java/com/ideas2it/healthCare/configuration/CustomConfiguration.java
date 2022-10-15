package com.ideas2it.healthCare.configuration;

import com.ideas2it.healthCare.dto.AppointmentDto;
import com.ideas2it.healthCare.dto.ClinicDto;
import com.ideas2it.healthCare.dto.DoctorClinicDto;
import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.dto.FeedbackDto;
import com.ideas2it.healthCare.dto.SpecializationDto;
import com.ideas2it.healthCare.model.Appointment;
import com.ideas2it.healthCare.model.Clinic;
import com.ideas2it.healthCare.model.Doctor;
import com.ideas2it.healthCare.model.DoctorClinic;
import com.ideas2it.healthCare.model.Feedback;
import com.ideas2it.healthCare.model.Specialization;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
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
                skip(destination.getPatient().getFeedback());
                skip(destination.getDoctor().getSpecializations());
                skip(destination.getDoctor().getAppointments());
                skip(destination.getPatient().getAppointment());
                skip(destination.getPatient().getVitals());
            }
        });

        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentDto>() {
            @Override
            protected void configure() {
                skip(destination.getPatient().getFeedback());
                skip(destination.getPatient().getAppointment());
                skip(destination.getPatient().getVitals());
                skip(destination.getDoctor().getSpecializations());
                skip(destination.getDoctor().getAppointments());
                skip(destination.getDoctor().getFeedbacks());
            }
        });

        modelMapper.addMappings(new PropertyMap<Specialization, SpecializationDto>() {
            @Override
            protected void configure() {

                //destination.setVitals();
            }
        });
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}

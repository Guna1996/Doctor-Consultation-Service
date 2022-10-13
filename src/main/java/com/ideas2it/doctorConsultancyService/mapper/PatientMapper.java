package com.ideas2it.doctorConsultancyService.mapper;

import com.ideas2it.doctorConsultancyService.dto.PatientDto;
import com.ideas2it.doctorConsultancyService.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PatientMapper {

    public static Patient fromDto(PatientDto patientDto) {
        return Patient.builder()
                .id(patientDto.getId())
                .name(patientDto.getName())
                .dateOfBirth(patientDto.getDateOfBirth())
                .gender(patientDto.getGender())
                .mobileNumber(patientDto.getMobileNumber())
                .email(patientDto.getEmail())
                /*.feedback(patientDto.getFeedback().stream().map(FeedbackMapper::fromDto).collect(Collectors.toList()))*/
                .appointment(patientDto.getAppointment().stream().map(AppointmentMapper::fromDto).collect(Collectors.toList()))
               /* .vitals(patientDto.getVitals().stream().map(VitalsMapper::fromDto).collect(Collectors.toList()))*/
                .build();
    }

    public static PatientDto toDto(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .gender(patient.getGender())
                .mobileNumber(patient.getMobileNumber())
                .email(patient.getEmail())
                /*.feedback(patient.getFeedback().stream().map(FeedbackMapper::toDto).collect(Collectors.toList()))*/
                .appointment(patient.getAppointment().stream().map(AppointmentMapper::toDto).collect(Collectors.toList()))
                /*.vitals(patient.getVitals().stream().map(VitalsMapper::toDto).collect(Collectors.toList()))*/
                .build();
    }
}

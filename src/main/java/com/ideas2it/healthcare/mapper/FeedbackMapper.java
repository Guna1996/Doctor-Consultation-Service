package com.ideas2it.healthcare.mapper;

import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.model.Feedback;
import com.ideas2it.healthcare.model.Patient;

public class FeedbackMapper {
    public static Feedback fromDto(FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        if(null != feedbackDto) {
            feedback.setId(feedbackDto.getId());
            feedback.setComment(feedbackDto.getComment());
            feedback.setRating(feedbackDto.getRating());
            feedback.setStatus(feedbackDto.getStatus());
            DoctorDto doctorDto = feedbackDto.getDoctor();
            if (doctorDto != null) {
                Doctor doctor = new Doctor();
                doctor.setId(doctorDto.getId());
                doctor.setName(doctorDto.getName());
                doctor.setDateOfBirth(doctorDto.getDateOfBirth());
                doctor.setGender(doctorDto.getGender());
                doctor.setQualification(doctorDto.getQualification());
                doctor.setDateOfRegistration(doctorDto.getDateOfRegistration());
                doctor.setMobileNumber(doctorDto.getMobileNumber());
                doctor.setCity(doctorDto.getCity());
                doctor.setStatus(doctorDto.getStatus());
                feedback.setDoctor(doctor);
            }
            PatientDto patientDto = feedbackDto.getPatient();
            if(null != patientDto) {
                Patient patient = new Patient();
                patient.setId(patient.getId());
                patient.setName(patientDto.getName());
                patient.setDateOfBirth(patientDto.getDateOfBirth());
                patient.setMobileNumber(patientDto.getMobileNumber());
                patient.setGender(patientDto.getGender());
                patient.setEmail(patientDto.getEmail());
                patient.setStatus(patientDto.getStatus());
                feedback.setPatient(patient);
            }
        }
        return feedback;
    }
    public static FeedbackDto toDto(Feedback feedback) {
        FeedbackDto feedbackDto = new FeedbackDto();
        if(null != feedback) {
            feedbackDto.setId(feedback.getId());
            feedbackDto.setComment(feedback.getComment());
            feedbackDto.setRating(feedback.getRating());
            feedbackDto.setStatus(feedback.getStatus());
            Doctor doctor = feedback.getDoctor();
            if(null != doctor) {
                DoctorDto doctorDto =new DoctorDto();
                doctorDto.setId(doctor.getId());
                doctorDto.setName(doctor.getName());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setDateOfBirth(doctor.getDateOfBirth());
                doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
                doctorDto.setQualification(doctor.getQualification());
                doctorDto.setMobileNumber(doctor.getMobileNumber());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setStatus(doctor.getStatus());
                feedbackDto.setDoctor(doctorDto);
            }
            Patient patient = feedback.getPatient();
            if(null != patient) {
                PatientDto patientDto = new PatientDto();
                patientDto.setId(patient.getId());
                patientDto.setName(patient.getName());
                patientDto.setDateOfBirth(patient.getDateOfBirth());
                patientDto.setMobileNumber(patient.getMobileNumber());
                patientDto.setGender(patient.getGender());
                patientDto.setEmail(patient.getEmail());
                patientDto.setStatus(patient.getStatus());
                feedbackDto.setPatient(patientDto);
            }
        }
        return feedbackDto;
    }
}

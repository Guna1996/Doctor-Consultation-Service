/**
 * <p>
 * This is the base package for all the mapper classes
 * which is for DoctorMapper, PatientMapper and ClinicMapper
 * classes
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.mapper;

import com.ideas2it.healthCare.dto.AppointmentDto;
import com.ideas2it.healthCare.dto.DoctorClinicDto;
import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.dto.FeedbackDto;
import com.ideas2it.healthCare.dto.SpecializationDto;
import com.ideas2it.healthCare.model.Appointment;
import com.ideas2it.healthCare.model.Doctor;
import com.ideas2it.healthCare.model.DoctorClinic;
import com.ideas2it.healthCare.model.Feedback;
import com.ideas2it.healthCare.model.Specialization;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * DoctorMapper is used convert Doctor object to
 * DoctorDto and DoctorDto into Doctor
 * </p>
 *
 * @author Mohamed Jubair
 *
 * @version 1
 *
 * @since 2022-07-18
 */
@Component
public class DoctorMapper {

    public static Doctor fromDto(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        if (doctorDto != null) {
            doctor.setId(doctorDto.getId());
            doctor.setName(doctorDto.getName());
            doctor.setDateOfBirth(doctorDto.getDateOfBirth());
            doctor.setGender(doctorDto.getGender());
            doctor.setQualification(doctorDto.getQualification());
            Set<SpecializationDto> specializationsDto = doctorDto.getSpecializations();
            if (specializationsDto != null) {
                Set<Specialization> specializations = new HashSet<>();
                        specializationsDto.forEach(specializationDto -> {
                            Specialization specialization = new Specialization();
                            specialization.setId(specializationDto.getId());
                            specialization.setName(specializationDto.getName());
                            specialization.setStatus(specializationDto.getStatus());
                            specializations.add(specialization);
                });
                doctor.setSpecializations(specializations);
            }
            doctor.setDateOfRegistration(doctorDto.getDateOfRegistration());
            doctor.setMobileNumber(doctorDto.getMobileNumber());
            doctor.setCity(doctorDto.getCity());
            doctor.setStatus(doctorDto.getStatus());
            List<FeedbackDto> feedbacksDto = doctorDto.getFeedbacks();
            if (feedbacksDto != null) {
                List<Feedback> feedbacks = new ArrayList<>();
                feedbacksDto.forEach(feedbackDto -> {
                    Feedback feedback = new Feedback();
                    feedback.setId(feedbackDto.getId());
                    feedback.setComment(feedbackDto.getComment());
                    feedback.setRating(feedbackDto.getRating());
                    feedback.setStatus(feedbackDto.getStatus());
                    feedbacks.add(feedback);
                });
                doctor.setFeedbacks(feedbacks);
            }
            List<AppointmentDto> appointmentsDto = doctorDto.getAppointments();
            if (appointmentsDto != null) {
                List<Appointment> appointments = new ArrayList<>();
                appointmentsDto.forEach(appointmentDto -> {
                    Appointment appointment = new Appointment();
                    appointment.setId(appointmentDto.getId());
                    appointment.setScheduledOn(appointmentDto.getScheduledOn());
                    appointments.add(appointment);
                });
                doctor.setAppointments(appointments);
            }
            List<DoctorClinicDto> doctorClinicsDto = doctorDto.getClinics();
            if (doctorClinicsDto != null) {
                List<DoctorClinic> doctorClinics = new ArrayList<>();
                doctorClinicsDto.forEach(doctorClinicDto -> {
                    DoctorClinic doctorClinic = new DoctorClinic();
                    doctorClinic.setId(doctorClinicDto.getId());
                    doctorClinic.setStatus(doctorClinicDto.getStatus());
                    doctorClinics.add(doctorClinic);
                });
                doctor.setClinics(doctorClinics);
            }
        }
    return doctor;
    }

    public static DoctorDto toDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        if (doctor != null) {
            doctorDto.setId(doctor.getId());
            doctorDto.setName(doctor.getName());
            doctorDto.setDateOfBirth(doctor.getDateOfBirth());
            doctorDto.setGender(doctor.getGender());
            doctorDto.setQualification(doctor.getQualification());
            Set<Specialization> specializations = doctor.getSpecializations();
            if (specializations != null) {
                Set<SpecializationDto> specializationsDto = new HashSet<>();
                specializations.forEach(specialization -> {
                    SpecializationDto specializationDto = new SpecializationDto();
                    specializationDto.setId(specialization.getId());
                    specializationDto.setName(specialization.getName());
                    specializationDto.setStatus(specialization.getStatus());
                    specializationsDto.add(specializationDto);
                });
                doctorDto.setSpecializations(specializationsDto);
            }
            doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
            doctorDto.setMobileNumber(doctor.getMobileNumber());
            doctorDto.setCity(doctor.getCity());
            doctorDto.setStatus(doctor.getStatus());
            List<Feedback> feedbacks = doctor.getFeedbacks();
            if (feedbacks != null) {
                List<FeedbackDto> feedbacksDto = new ArrayList<>();
                feedbacks.forEach(feedback -> {
                    FeedbackDto feedbackDto = new FeedbackDto();
                    feedbackDto.setId(feedback.getId());
                    feedbackDto.setComment(feedback.getComment());
                    feedbackDto.setRating(feedback.getRating());
                    feedbackDto.setStatus(feedback.getStatus());
                    feedbacksDto.add(feedbackDto);
                });
                doctorDto.setFeedbacks(feedbacksDto);
            }
            List<Appointment> appointments = doctor.getAppointments();
            if (appointments != null) {
                List<AppointmentDto> appointmentsDto = new ArrayList<>();
                appointments.forEach(appointment -> {
                    AppointmentDto appointmentDto = new AppointmentDto();
                    appointmentDto.setId(appointment.getId());
                    appointmentDto.setScheduledOn(appointment.getScheduledOn());
                    appointmentsDto.add(appointmentDto);
                });
                doctorDto.setAppointments(appointmentsDto);
            }
            List<DoctorClinic> doctorClinics = doctor.getClinics();
            if (doctorClinics != null) {
                List<DoctorClinicDto> doctorClinicsDto = new ArrayList<>();
                doctorClinics.forEach(doctorClinic -> {
                    DoctorClinicDto doctorClinicDto = new DoctorClinicDto();
                    doctorClinicDto.setId(doctorClinic.getId());
                    doctorClinicDto.setStatus(doctorClinic.getStatus());
                    doctorClinicsDto.add(doctorClinicDto);
                });
                doctorDto.setClinics(doctorClinicsDto);
            }
        }
        return doctorDto;
    }
}

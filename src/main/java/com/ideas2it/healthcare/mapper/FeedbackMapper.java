/**
 * <p>
 * This is the base package for all the mapper classes
 * which is for Doctor mapper, Patient mapper and Clinic mapper
 * classes
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.mapper;

import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.model.Appointment;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.model.Feedback;
import com.ideas2it.healthcare.model.Patient;
import com.ideas2it.healthcare.util.DateUtil;

/**
 * <p>
 * Feedback mapper class is used convert Feedback object into
 * Feedback dto and also used to convert Feedback dto into Feedback model
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public class FeedbackMapper {

    /**
     * <p>
     * This method is used to convert Feedback dto into
     * Feedback model
     * </p>
     *
     * @param feedbackDto {@link FeedbackDto} contains feedback details
     * @return {@link Feedback}
     */
    public static Feedback fromDto(FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        if (null != feedbackDto) {
            feedback.setId(feedbackDto.getId());
            feedback.setComment(feedbackDto.getComment());
            feedback.setRating(feedbackDto.getRating());
            feedback.setStatus(feedbackDto.getStatus());
            DoctorDto doctorDto = feedbackDto.getDoctor();
            if (null != doctorDto) {
                Doctor doctor = new Doctor();
                doctor.setId(doctorDto.getId());
                doctor.setName(doctorDto.getName());
                doctor.setConsultationFee(doctorDto.getConsultationFee());
                doctor.setDateOfBirth(doctorDto.getDateOfBirth());
                doctor.setGender(doctorDto.getGender());
                doctor.setQualification(doctorDto.getQualification());
                doctor.setDateOfRegistration(doctorDto.getDateOfRegistration());
                if (null != doctor.getMobileNumber()) {
                    doctor.setMobileNumber(Long.parseLong(doctorDto.getMobileNumber()));
                }
                doctor.setCity(doctorDto.getCity());
                doctor.setStatus(doctorDto.getStatus());
                feedback.setDoctor(doctor);
            }
            PatientDto patientDto = feedbackDto.getPatient();
            if (null != patientDto) {
                Patient patient = new Patient();
                patient.setId(patientDto.getId());
                patient.setName(patientDto.getName());
                patient.setDateOfBirth(patientDto.getDateOfBirth());
                if (null != patientDto.getMobileNumber()) {
                    patient.setMobileNumber(Long.parseLong(patientDto.getMobileNumber()));
                }
                patient.setGender(patientDto.getGender());
                patient.setEmail(patientDto.getEmail());
                patient.setStatus(patientDto.getStatus());
                feedback.setPatient(patient);
            }
            Appointment appointment = new Appointment();
            AppointmentDto appointmentDto = feedbackDto.getAppointment();
            if (null != appointmentDto) {
                appointment.setStatus(appointmentDto.getStatus());
                appointment.setCreatedAt(appointmentDto.getCreatedAt());
                appointment.setId(appointmentDto.getId());
                appointment.setScheduledOn(appointmentDto.getScheduledOn());
                appointment.setTimeFormat(appointmentDto.getTimeFormat());
                feedback.setAppointment(appointment);
            }
        }
        return feedback;
    }

    /**
     * <p>
     * This method is used to convert Feedback model into
     * Feedback dto
     * </p>
     *
     * @param feedback {@link Feedback} contains feedback details
     * @return {@link FeedbackDto}
     */
    public static FeedbackDto toDto(Feedback feedback) {
        FeedbackDto feedbackDto = new FeedbackDto();
        if (null != feedback) {
            feedbackDto.setId(feedback.getId());
            feedbackDto.setComment(feedback.getComment());
            feedbackDto.setRating(feedback.getRating());
            feedbackDto.setStatus(feedback.getStatus());
            Doctor doctor = feedback.getDoctor();
            if (null != doctor) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setId(doctor.getId());
                doctorDto.setName(doctor.getName());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setConsultationFee(doctor.getConsultationFee());
                doctorDto.setDateOfBirth(doctor.getDateOfBirth());
                if (null != doctor.getDateOfBirth()) {
                    doctorDto.setAge(DateUtil.getDifferenceInYears(doctor.getDateOfBirth()));
                }
                doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
                if (null != doctor.getDateOfRegistration()) {
                    doctorDto.setExperience(DateUtil.getDifferenceInYears(doctor.getDateOfRegistration()));
                }
                doctorDto.setQualification(doctor.getQualification());
                if (null != doctor.getMobileNumber()) {
                    doctorDto.setMobileNumber(Long.toString(doctor.getMobileNumber()));
                }
                doctorDto.setGender(doctor.getGender());
                doctorDto.setStatus(doctor.getStatus());
                feedbackDto.setDoctor(doctorDto);
            }
            Patient patient = feedback.getPatient();
            if (null != patient) {
                PatientDto patientDto = new PatientDto();
                patientDto.setId(patient.getId());
                patientDto.setName(patient.getName());
                patientDto.setDateOfBirth(patient.getDateOfBirth());
                if (null != patient.getMobileNumber()) {
                    patientDto.setMobileNumber(Long.toString(patient.getMobileNumber()));
                }
                patientDto.setGender(patient.getGender());
                patientDto.setEmail(patient.getEmail());
                patientDto.setStatus(patient.getStatus());
                feedbackDto.setPatient(patientDto);
            }
            Appointment appointment = feedback.getAppointment();
            AppointmentDto appointmentDto = new AppointmentDto();
            if (null != appointment) {
                appointmentDto.setStatus(appointment.getStatus());
                appointmentDto.setCreatedAt(appointment.getCreatedAt());
                appointmentDto.setId(appointment.getId());
                appointmentDto.setScheduledOn(appointment.getScheduledOn());
                appointmentDto.setTimeFormat(appointment.getTimeFormat());
                feedbackDto.setAppointment(appointmentDto);
            }
        }
        return feedbackDto;
    }
}

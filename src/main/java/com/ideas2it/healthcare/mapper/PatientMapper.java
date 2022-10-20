/**
 * <p>
 * This is the base package for all the mapper classes
 * which is for DoctorMapper, PatientMapper and ClinicMapper
 * classes
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.mapper;

import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.model.Appointment;
import com.ideas2it.healthcare.model.Feedback;
import com.ideas2it.healthcare.model.Patient;
import com.ideas2it.healthcare.model.Vital;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DoctorClinicMapper is used convert DoctorClinic object to
 * DoctorClinicDto and DoctorClinicDto into DoctorClinic
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
public class PatientMapper {

    /**
     * <p>
     * This method is used to convert PatientDto to
     * Patient model
     * </p>
     *
     * @return {@link Patient}
     */
    public static Patient fromDto(PatientDto patientDto) {
        Patient patient = new Patient();
        if (null != patientDto) {
            patient.setId(patientDto.getId());
            patient.setName(patientDto.getName());
            patient.setDateOfBirth(patientDto.getDateOfBirth());
            patient.setGender(patientDto.getGender());
            patient.setMobileNumber(patientDto.getMobileNumber());
            patient.setEmail(patientDto.getEmail());
            patient.setStatus(patientDto.getStatus());
            List<FeedbackDto> feedbacksDto = patientDto.getFeedbacks();
            if (null != feedbacksDto) {
                List<Feedback> feedbacks = new ArrayList<>();
                feedbacksDto.forEach(feedbackDto -> {
                    Feedback feedback = new Feedback();
                    feedback.setId(feedbackDto.getId());
                    feedback.setComment(feedbackDto.getComment());
                    feedback.setRating(feedbackDto.getRating());
                    feedback.setStatus(feedbackDto.getStatus());
                    feedbacks.add(feedback);
                });
                patient.setFeedbacks(feedbacks);
            }
            List<AppointmentDto> appointmentsDto = patientDto.getAppointment();
            if (null != appointmentsDto) {
                List<Appointment> appointments = new ArrayList<>();
                appointmentsDto.forEach(appointmentDto -> {
                    Appointment appointment = new Appointment();
                    appointment.setId(appointmentDto.getId());
                    appointment.setScheduledOn(appointmentDto.getScheduledOn());
                    appointment.setStatus(appointmentDto.getStatus());
                    appointments.add(appointment);
                });
                patient.setAppointment(appointments);
            }
            List<VitalDto> vitalsDto = patientDto.getVitals();
            if (null != vitalsDto) {
                List<Vital> vitals = new ArrayList<>();
                vitalsDto.forEach(vitalDto -> {
                    Vital vital = new Vital();
                    vital.setId(vitalDto.getId());
                    vital.setHeight(vitalDto.getHeight());
                    vital.setWeight(vitalDto.getWeight());
                    vital.setPulse(vitalDto.getPulse());
                    vital.setDiastolic(vitalDto.getDiastolic());
                    vital.setSystolic(vitalDto.getSystolic());
                    vital.setSugarLevel(vitalDto.getSugarLevel());
                    vital.setStatus(vitalDto.getStatus());
                    vital.setBloodPressure(vitalDto.getBloodPressure());
                    vitals.add(vital);
                });
                patient.setVitals(vitals);
            }
        }
        return patient;
    }

    /**
     * <p>
     * This method is used to convert Patient to
     * PatientDto
     * </p>
     *
     * @return {@link PatientDto}
     */
    public static PatientDto toDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        if (null != patient) {
            patientDto.setId(patient.getId());
            patientDto.setName(patient.getName());
            patientDto.setDateOfBirth(patient.getDateOfBirth());
            patientDto.setGender(patient.getGender());
            patientDto.setMobileNumber(patient.getMobileNumber());
            patientDto.setEmail(patient.getEmail());
            patientDto.setStatus(patient.getStatus());
            List<Feedback> feedbacks = patient.getFeedbacks();
            if (null != feedbacks) {
                List<FeedbackDto> feedbacksDto = new ArrayList<>();
                feedbacks.forEach(feedback -> {
                    FeedbackDto feedbackDto = new FeedbackDto();
                    feedbackDto.setId(feedback.getId());
                    feedbackDto.setComment(feedback.getComment());
                    feedbackDto.setRating(feedback.getRating());
                    feedbackDto.setStatus(feedback.getStatus());
                    feedbacksDto.add(feedbackDto);
                });
                patientDto.setFeedbacks(feedbacksDto);
            }
            List<Appointment> appointments = patient.getAppointment();
            if (null != appointments) {
                List<AppointmentDto> appointmentsDtoList = new ArrayList<>();
                appointments.forEach(appointment -> {
                    AppointmentDto appointmentDto = new AppointmentDto();
                    appointmentDto.setId(appointment.getId());
                    appointmentDto.setScheduledOn(appointment.getScheduledOn());
                    appointmentDto.setStatus(appointment.getStatus());
                    appointmentsDtoList.add(appointmentDto);
                });
                patientDto.setAppointment(appointmentsDtoList);
            }
            List<Vital> vitals = patient.getVitals();
            if (null != vitals) {
                List<VitalDto> vitalsDto = new ArrayList<>();
                vitals.forEach(vital -> {
                    VitalDto vitaldto = new VitalDto();
                    vitaldto.setId(vital.getId());
                    vitaldto.setHeight(vital.getHeight());
                    vitaldto.setWeight(vital.getWeight());
                    vitaldto.setPulse(vital.getPulse());
                    vitaldto.setDiastolic(vital.getDiastolic());
                    vitaldto.setSystolic(vital.getSystolic());
                    vitaldto.setSugarLevel(vital.getSugarLevel());
                    vitaldto.setStatus(vital.getStatus());
                    vitaldto.setBloodPressure(vitaldto.getBloodPressure());
                    vitalsDto.add(vitaldto);
                });
                patientDto.setVitals(vitalsDto);
            }
        }
        return patientDto;
    }
}

package com.ideas2it.healthCare.mapper;

import com.ideas2it.healthCare.dto.AppointmentDto;
import com.ideas2it.healthCare.dto.FeedbackDto;
import com.ideas2it.healthCare.dto.PatientDto;
import com.ideas2it.healthCare.dto.VitalsDto;
import com.ideas2it.healthCare.model.Appointment;
import com.ideas2it.healthCare.model.Feedback;
import com.ideas2it.healthCare.model.Patient;
import com.ideas2it.healthCare.model.Vitals;

import java.util.ArrayList;
import java.util.List;

public class PatientMapper {

    public static Patient fromDto(PatientDto patientDto) {
        Patient patient = new Patient();
        if(null != patientDto) {
            patient.setId(patientDto.getId());
            patient.setName(patientDto.getName());
            patient.setDateOfBirth(patientDto.getDateOfBirth());
            patient.setGender(patientDto.getGender());
            patient.setMobileNumber(patientDto.getMobileNumber());
            patient.setEmail(patientDto.getEmail());
            patient.setStatus(patientDto.getStatus());
            List<FeedbackDto> feedbacksDto =  patientDto.getFeedbacks();
            if(null != feedbacksDto) {
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
            List <AppointmentDto> appointmentsDto = patientDto.getAppointment();
            if (null != appointmentsDto) {
                List<Appointment> appointments = new ArrayList<>();
                appointmentsDto.forEach(appointmentDto -> {
                    Appointment appointment = new Appointment();
                    appointment.setId(appointmentDto.getId());
                    appointment.setScheduledOn(appointmentDto.getScheduledOn());
                    appointment.setCreatedAt(appointmentDto.getCreatedAt());
                    appointment.setStatus(appointmentDto.getStatus());
                    appointments.add(appointment);
                });
                patient.setAppointment(appointments);
            }
            List<VitalsDto> vitalsDto = patientDto.getVitals();
            if(null != vitalsDto) {
                List<Vitals> vitals = new ArrayList<>();
                vitalsDto.forEach(vitalsdto -> {
                    Vitals vital = new Vitals();
                    vital.setId(vitalsdto.getId());
                    vital.setHeight(vitalsdto.getHeight());
                    vital.setWeight(vitalsdto.getWeight());
                    vital.setPulse(vitalsdto.getPulse());
                    vital.setDiastolic(vitalsdto.getDiastolic());
                    vital.setSystolic(vitalsdto.getSystolic());
                    vital.setSugarLevel(vitalsdto.getSugarLevel());
                    vital.setStatus(vitalsdto.getStatus());
                    vital.setBloodPressure(vitalsdto.getBloodPressure());
                    vitals.add(vital);
                });
                patient.setVitals(vitals);
            }
        }
        return patient;
    }

    public static PatientDto toDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        if(null != patient) {
            patientDto.setId(patient.getId());
            patientDto.setName(patient.getName());
            patientDto.setDateOfBirth(patient.getDateOfBirth());
            patientDto.setGender(patient.getGender());
            patientDto.setMobileNumber(patient.getMobileNumber());
            patientDto.setEmail(patient.getEmail());
            patientDto.setStatus(patient.getStatus());
            List<Feedback> feedbacks =  patient.getFeedbacks();
            if(null != feedbacks) {
                List<FeedbackDto> feedbacksDto = new ArrayList<>();
                feedbacks.forEach(feedback -> {
                    FeedbackDto feedbacksdto = new FeedbackDto();
                    feedbacksdto.setId(feedback.getId());
                    feedbacksdto.setComment(feedback.getComment());
                    feedbacksdto.setRating(feedback.getRating());
                    feedbacksdto.setStatus(feedback.getStatus());
                    feedbacksDto.add(feedbacksdto);
                });
                patientDto.setFeedbacks(feedbacksDto);
            }
            List <Appointment> appointments = patient.getAppointment();
            if (null != appointments) {
                List<AppointmentDto> appointmentsDtoList = new ArrayList<>();
                appointments.forEach(appointment -> {
                    AppointmentDto appointmentDto = new AppointmentDto();
                    appointmentDto.setId(appointment.getId());
                    appointmentDto.setScheduledOn(appointment.getScheduledOn());
                    appointmentDto.setCreatedAt(appointment.getCreatedAt());
                    appointmentDto.setStatus(appointment.getStatus());
                    appointmentsDtoList.add(appointmentDto);
                });
                patientDto.setAppointment(appointmentsDtoList);
            }
            List<Vitals> vitals = patient.getVitals();
            if(null != vitals) {
                List<VitalsDto> vitalsDtos = new ArrayList<>();
                vitals.forEach(vital -> {
                    VitalsDto vitalsdto = new VitalsDto();
                    vitalsdto.setId(vital.getId());
                    vitalsdto.setHeight(vital.getHeight());
                    vitalsdto.setWeight(vital.getWeight());
                    vitalsdto.setPulse(vital.getPulse());
                    vitalsdto.setDiastolic(vital.getDiastolic());
                    vitalsdto.setSystolic(vital.getSystolic());
                    vitalsdto.setSugarLevel(vital.getSugarLevel());
                    vitalsdto.setStatus(vital.getStatus());
                    vitalsdto.setBloodPressure(vitalsdto.getBloodPressure());
                    vitalsDtos.add(vitalsdto);
                });
                patientDto.setVitals(vitalsDtos);
            }
        }
        return patientDto;
    }
}

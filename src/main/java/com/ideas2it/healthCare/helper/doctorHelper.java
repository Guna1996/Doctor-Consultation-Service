package com.ideas2it.healthCare.helper;

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class doctorHelper {

    public Doctor fromDto(DoctorDto doctorDto) {
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
}

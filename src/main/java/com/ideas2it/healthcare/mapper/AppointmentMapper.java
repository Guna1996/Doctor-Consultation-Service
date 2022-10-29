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
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.model.Appointment;
import com.ideas2it.healthcare.model.Clinic;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.model.Patient;

/**
 * <p>
 * AppointmentMapper is used convert Appointment object to
 * AppointmentDto and AppointmentDto into Appointment
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public class AppointmentMapper {

    /**
     * <p>
     * This method is used to convert AppointmentDto to
     * Appointment model
     * </p>
     *
     * @return {@link Appointment}
     */
    public static Appointment fromDto(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        if (appointmentDto != null) {
            appointment.setId(appointmentDto.getId());
            appointment.setScheduledOn(appointmentDto.getScheduledOn());
            appointment.setStatus(appointmentDto.getStatus());
            PatientDto patientDto = appointmentDto.getPatient();
            if (patientDto != null) {
                Patient patient = new Patient();
                patient.setId(patientDto.getId());
                patient.setName(patientDto.getName());
                patient.setDateOfBirth(patientDto.getDateOfBirth());
                patient.setGender(patientDto.getGender());
                patient.setMobileNumber(patientDto.getMobileNumber());
                patient.setEmail(patientDto.getEmail());
                appointment.setPatient(patient);
            }

            DoctorDto doctorDto = appointmentDto.getDoctor();
            if (doctorDto != null) {
                Doctor doctor = new Doctor();
                doctor.setId(doctorDto.getId());
                doctor.setName(doctorDto.getName());
                doctor.setDateOfBirth(doctorDto.getDateOfBirth());
                doctor.setGender(doctorDto.getGender());
                doctor.setConsultationFee(doctorDto.getConsultationFee());
                doctor.setQualification(doctorDto.getQualification());
                doctor.setDateOfRegistration(doctorDto.getDateOfRegistration());
                doctor.setMobileNumber(doctorDto.getMobileNumber());
                doctor.setCity(doctorDto.getCity());
                doctor.setStatus(doctorDto.getStatus());
                appointment.setDoctor(doctor);
            }

            ClinicDto clinicDto = appointmentDto.getClinic();
            if (clinicDto != null) {
                Clinic clinic = new Clinic();
                clinic.setId(clinicDto.getId());
                clinic.setName(clinicDto.getName());
                clinic.setDoorNumber(clinicDto.getDoorNumber());
                clinic.setStreetName(clinicDto.getStreetName());
                clinic.setCity(clinicDto.getCity());
                clinic.setState(clinicDto.getState());
                clinic.setPinCode(clinicDto.getPinCode());
                clinic.setContactNumber(clinicDto.getContactNumber());
                appointment.setClinic(clinic);
            }
        }
        return appointment;
    }

    /**
     * <p>
     * This method is used to convert VitalDto to
     * Vital model
     * </p>
     *
     * @return {@link AppointmentDto}
     */
    public static AppointmentDto toDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        if (appointment != null) {
            appointmentDto.setId(appointment.getId());
            appointmentDto.setScheduledOn(appointment.getScheduledOn());
            appointmentDto.setCreatedAt(appointment.getCreatedAt());
            appointmentDto.setStatus(appointment.getStatus());
            Patient patient = appointment.getPatient();
            if (patient != null) {
                PatientDto patientDto = new PatientDto();
                patientDto.setId(patient.getId());
                patientDto.setName(patient.getName());
                patientDto.setDateOfBirth(patient.getDateOfBirth());
                patientDto.setGender(patient.getGender());
                patientDto.setMobileNumber(patient.getMobileNumber());
                patientDto.setEmail(patient.getEmail());
                appointmentDto.setPatient(patientDto);
            }

            Doctor doctor = appointment.getDoctor();
            if (doctor != null) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setId(doctor.getId());
                doctorDto.setName(doctor.getName());
                doctorDto.setDateOfBirth(doctor.getDateOfBirth());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setQualification(doctor.getQualification());
                doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
                doctorDto.setMobileNumber(doctor.getMobileNumber());
                doctorDto.setCity(doctor.getCity());
                doctorDto.setStatus(doctor.getStatus());
                doctorDto.setConsultationFee(doctor.getConsultationFee());
                appointmentDto.setDoctor(doctorDto);
            }

            Clinic clinic = appointment.getClinic();
            if (clinic != null) {
                ClinicDto clinicDto = new ClinicDto();
                clinicDto.setId(clinic.getId());
                clinicDto.setName(clinic.getName());
                clinicDto.setDoorNumber(clinic.getDoorNumber());
                clinicDto.setStreetName(clinic.getStreetName());
                clinicDto.setCity(clinic.getCity());
                clinicDto.setState(clinic.getState());
                clinicDto.setPinCode(clinic.getPinCode());
                clinicDto.setContactNumber(clinic.getContactNumber());
                appointmentDto.setClinic(clinicDto);
            }
        }
        return appointmentDto;
    }
}

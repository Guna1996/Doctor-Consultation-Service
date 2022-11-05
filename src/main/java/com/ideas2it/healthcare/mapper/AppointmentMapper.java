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
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.model.Appointment;
import com.ideas2it.healthcare.model.Clinic;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.model.Patient;
import com.ideas2it.healthcare.util.DateUtil;

/**
 * <p>
 * Appointment mapper is used convert Appointment object to
 * Appointment dto and Appointment dto into Appointment
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public class AppointmentMapper {

    /**
     * <p>
     * This method is used to convert Appointment dto into
     * Appointment model
     * </p>
     *
     * @parm appointmentDto {@link AppointmentDto} contains appointment details
     * @return {@link Appointment}
     */
    public static Appointment fromDto(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        if (null != appointmentDto) {
            appointment.setId(appointmentDto.getId());
            appointment.setScheduledOn(appointmentDto.getScheduledOn());
            appointment.setStatus(appointmentDto.getStatus());
            PatientDto patientDto = appointmentDto.getPatient();
            if (null != patientDto) {
                Patient patient = new Patient();
                patient.setId(patientDto.getId());
                patient.setName(patientDto.getName());
                patient.setDateOfBirth(patientDto.getDateOfBirth());
                patient.setGender(patientDto.getGender());
                if (null != patientDto.getMobileNumber()) {
                    patient.setMobileNumber(Long.parseLong(patientDto.getMobileNumber()));
                }
                patient.setEmail(patientDto.getEmail());
                appointment.setPatient(patient);
            }

            DoctorDto doctorDto = appointmentDto.getDoctor();
            if (null != doctorDto) {
                Doctor doctor = new Doctor();
                doctor.setId(doctorDto.getId());
                doctor.setName(doctorDto.getName());
                doctor.setDateOfBirth(doctorDto.getDateOfBirth());
                doctor.setGender(doctorDto.getGender());
                doctor.setConsultationFee(doctorDto.getConsultationFee());
                doctor.setQualification(doctorDto.getQualification());
                doctor.setDateOfRegistration(doctorDto.getDateOfRegistration());
                if (null != doctorDto.getMobileNumber()) {
                    doctor.setMobileNumber(Long.parseLong(doctorDto.getMobileNumber()));
                }
                doctor.setCity(doctorDto.getCity());
                doctor.setStatus(doctorDto.getStatus());
                appointment.setDoctor(doctor);
            }

            ClinicDto clinicDto = appointmentDto.getClinic();
            if (null != clinicDto) {
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
     * This method is used to convert Appointment model into
     * Appointment dto
     * </p>
     *
     * @param appointment {@link Appointment} contains appointment details
     * @return {@link AppointmentDto}
     */
    public static AppointmentDto toDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        if (null != appointment) {
            appointmentDto.setId(appointment.getId());
            appointmentDto.setScheduledOn(appointment.getScheduledOn());
            appointmentDto.setCreatedAt(appointment.getCreatedAt());
            appointmentDto.setStatus(appointment.getStatus());
            Patient patient = appointment.getPatient();
            if (null != patient) {
                PatientDto patientDto = new PatientDto();
                patientDto.setId(patient.getId());
                patientDto.setName(patient.getName());
                patientDto.setDateOfBirth(patient.getDateOfBirth());
                patientDto.setGender(patient.getGender());
                if(null != patient.getMobileNumber()) {
                    patientDto.setMobileNumber(Long.toString(patient.getMobileNumber()));
                }
                patientDto.setEmail(patient.getEmail());
                appointmentDto.setPatient(patientDto);
            }

            Doctor doctor = appointment.getDoctor();
            if (null != doctor) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setId(doctor.getId());
                doctorDto.setName(doctor.getName());
                doctorDto.setDateOfBirth(doctor.getDateOfBirth());
                if(null != doctor.getDateOfBirth()) {
                    doctorDto.setAge(DateUtil.getDifferenceInYears(doctor.getDateOfBirth()));
                }
                doctorDto.setGender(doctor.getGender());
                doctorDto.setQualification(doctor.getQualification());
                doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
                if(null != doctor.getDateOfRegistration()) {
                    doctorDto.setExperience(DateUtil.getDifferenceInYears(doctor.getDateOfRegistration()));
                }
                if(null != doctor.getMobileNumber()) {
                    doctorDto.setMobileNumber(Long.toString(doctor.getMobileNumber()));
                }
                doctorDto.setCity(doctor.getCity());
                doctorDto.setStatus(doctor.getStatus());
                doctorDto.setConsultationFee(doctor.getConsultationFee());
                appointmentDto.setDoctor(doctorDto);
            }

            Clinic clinic = appointment.getClinic();
            if (null != clinic) {
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

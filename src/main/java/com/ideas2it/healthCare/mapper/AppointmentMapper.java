package com.ideas2it.healthCare.mapper;

import com.ideas2it.healthCare.dto.AppointmentDto;
import com.ideas2it.healthCare.dto.ClinicDto;
import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.dto.PatientDto;
import com.ideas2it.healthCare.model.Appointment;
import com.ideas2it.healthCare.model.Clinic;
import com.ideas2it.healthCare.model.Doctor;
import com.ideas2it.healthCare.model.Patient;
import org.springframework.stereotype.Component;

public class AppointmentMapper {

    public static Appointment fromDto(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        if (appointmentDto != null) {
            appointment.setId(appointmentDto.getId());
            appointment.setScheduledOn(appointmentDto.getScheduledOn());
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

    public static AppointmentDto toDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        if (appointment != null) {
            appointmentDto.setId(appointment.getId());

            appointmentDto.setScheduledOn(appointment.getScheduledOn());

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

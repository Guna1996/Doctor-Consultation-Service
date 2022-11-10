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
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.dto.PatientVitalDto;
import com.ideas2it.healthcare.model.Appointment;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.model.Patient;
import com.ideas2it.healthcare.model.PatientVital;
import com.ideas2it.healthcare.util.DateUtil;
import com.ideas2it.healthcare.util.VitalUtil;

/**
 * <p>
 * Patient vital mapper is used convert Patient vital object into
 * Patient vital dto and Patient vital dto into Patient vital model.
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
public class PatientVitalMapper {

    /**
     * <p>
     * This method is used to convert Patient vital dto into
     * Patient vital model
     * </p>
     *
     * @param patientVitalDto {@link PatientVitalDto} contains vitals details
     * @return {@link PatientVital}
     */
    public static PatientVital fromDto(PatientVitalDto patientVitalDto) {
        PatientVital patientVital = new PatientVital();
        if (null != patientVitalDto) {
            patientVital.setId(patientVitalDto.getId());
            patientVital.setHeight(patientVitalDto.getHeight());
            patientVital.setWeight(patientVitalDto.getWeight());
            patientVital.setPulse(patientVitalDto.getPulse());
            patientVital.setSystolic(patientVitalDto.getSystolic());
            patientVital.setDiastolic(patientVitalDto.getDiastolic());
            patientVital.setSugarLevel(patientVitalDto.getSugarLevel());
            patientVital.setStatus(patientVitalDto.getStatus());
            patientVital.setCreatedAt(patientVitalDto.getCreatedAt());
            patientVital.setCreatedAt(patientVitalDto.getCreatedAt());
            patientVital.setUpdatedAt(patientVitalDto.getUpdatedAt());
            patientVital.setBpRiskLevel(VitalUtil.getBPRiskLevel(patientVitalDto.getSystolic(),
                    patientVitalDto.getDiastolic()));
            DoctorDto doctorDto = patientVitalDto.getDoctor();
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
                patientVital.setDoctor(doctor);
            }
            PatientDto patientDto = patientVitalDto.getPatient();
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
                patientVital.setPatient(patient);
            }
            Appointment appointment = new Appointment();
            AppointmentDto appointmentDto = patientVitalDto.getAppointment();
            if (null != appointmentDto) {
                appointment.setStatus(appointmentDto.getStatus());
                appointment.setCreatedAt(appointmentDto.getCreatedAt());
                appointment.setId(appointmentDto.getId());
                appointment.setScheduledAt(appointmentDto.getScheduledAt());
                appointment.setTimeFormat(appointmentDto.getTimeFormat());
                patientVital.setAppointment(appointment);
            }
        }
        return patientVital;
    }

    /**
     * <p>
     * This method is used to convert Patient vital model into
     * Patient vital dto.
     * </p>
     *
     * @param patientVital {@link PatientVital} contains vitals details
     * @return {@link PatientVitalDto}
     */
    public static PatientVitalDto toDto(PatientVital patientVital) {
        PatientVitalDto patientVitalDto = new PatientVitalDto();
        if (null != patientVital) {
            patientVitalDto.setId(patientVital.getId());
            patientVitalDto.setHeight(patientVital.getHeight());
            patientVitalDto.setWeight(patientVital.getWeight());
            patientVitalDto.setPulse(patientVital.getPulse());
            patientVitalDto.setSystolic(patientVital.getSystolic());
            patientVitalDto.setDiastolic(patientVital.getDiastolic());
            patientVitalDto.setSugarLevel(patientVital.getSugarLevel());
            patientVitalDto.setStatus(patientVital.getStatus());
            patientVitalDto.setBpRiskLevel(patientVital.getBpRiskLevel());
            patientVitalDto.setCreatedAt(patientVital.getCreatedAt());
            Doctor doctor = patientVital.getDoctor();
            if (null != doctor) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setId(doctor.getId());
                doctorDto.setName(doctor.getName());
                doctorDto.setGender(doctor.getGender());
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
                doctorDto.setCity(doctor.getCity());
                doctorDto.setConsultationFee(doctor.getConsultationFee());
                doctorDto.setStatus(doctor.getStatus());
                patientVitalDto.setDoctor(doctorDto);
            }
            Patient patient = patientVital.getPatient();
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
                patientVitalDto.setPatient(patientDto);
            }
            Appointment appointment = patientVital.getAppointment();
            AppointmentDto appointmentDto = new AppointmentDto();
            if (null != appointment) {
                appointmentDto.setStatus(appointment.getStatus());
                appointmentDto.setCreatedAt(appointment.getCreatedAt());
                appointmentDto.setId(appointment.getId());
                appointmentDto.setScheduledAt(appointment.getScheduledAt());
                appointmentDto.setTimeFormat(appointment.getTimeFormat());
                patientVitalDto.setAppointment(appointmentDto);
            }
        }
        return patientVitalDto;
    }
}

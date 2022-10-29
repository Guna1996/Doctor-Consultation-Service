/**
 * <p>
 * This is the base package for all the mapper classes
 * which is for DoctorMapper, PatientMapper and ClinicMapper
 * classes
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.mapper;

import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.model.Patient;
import com.ideas2it.healthcare.model.Vital;

/**
 * <p>
 * VitalMapper is used convert Vital object to
 * VitalDto and VitalDto into Vital
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
public class VitalsMapper {

    /**
     * <p>
     * This method is used to convert VitalDto to
     * Vital model
     * </p>
     *
     * @return {@link Vital}
     */
    public static Vital fromDto(VitalDto vitalDto) {
        Vital vital = new Vital();
        if (null != vitalDto) {
            vital.setId(vitalDto.getId());
            vital.setHeight(vitalDto.getHeight());
            vital.setWeight(vitalDto.getWeight());
            vital.setPulse(vitalDto.getPulse());
            vital.setSystolic(vitalDto.getSystolic());
            vital.setDiastolic(vitalDto.getDiastolic());
            vital.setSugarLevel(vitalDto.getSugarLevel());
            vital.setStatus(vitalDto.getStatus());
            vital.setCreatedAt(vitalDto.getCreatedAt());
            vital.setBloodPressure(vitalDto.getBloodPressure());
            DoctorDto doctorDto = vitalDto.getDoctor();
            if (null != doctorDto) {
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
                vital.setDoctor(doctor);
            }
            PatientDto patientDto = vitalDto.getPatient();
            if (null != patientDto) {
                Patient patient = new Patient();
                patient.setId(patientDto.getId());
                patient.setName(patientDto.getName());
                patient.setDateOfBirth(patientDto.getDateOfBirth());
                patient.setMobileNumber(patientDto.getMobileNumber());
                patient.setGender(patientDto.getGender());
                patient.setEmail(patientDto.getEmail());
                patient.setStatus(patientDto.getStatus());
                vital.setPatient(patient);
            }
        }
        return vital;
    }

    /**
     * <p>
     * This method is used to convert Vital to
     * VitalDto
     * </p>
     *
     * @return {@link VitalDto}
     */
    public static VitalDto toDto(Vital vital) {
        VitalDto vitalDto = new VitalDto();
        if (null != vital) {
            vitalDto.setId(vital.getId());
            vitalDto.setHeight(vital.getHeight());
            vitalDto.setWeight(vital.getWeight());
            vitalDto.setPulse(vital.getPulse());
            vitalDto.setSystolic(vital.getSystolic());
            vitalDto.setDiastolic(vital.getDiastolic());
            vitalDto.setSugarLevel(vital.getSugarLevel());
            vitalDto.setStatus(vital.getStatus());
            vitalDto.setBloodPressure(vital.getBloodPressure());
            vitalDto.setCreatedAt(vital.getCreatedAt());
            Doctor doctor = vital.getDoctor();
            if (null != doctor) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setId(doctor.getId());
                doctorDto.setName(doctor.getName());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setDateOfBirth(doctor.getDateOfBirth());
                doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
                doctorDto.setQualification(doctor.getQualification());
                doctorDto.setMobileNumber(doctor.getMobileNumber());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setCity(doctor.getCity());
                doctorDto.setConsultationFee(doctor.getConsultationFee());
                doctorDto.setStatus(doctor.getStatus());
                vitalDto.setDoctor(doctorDto);
            }
            Patient patient = vital.getPatient();
            if (null != patient) {
                PatientDto patientDto = new PatientDto();
                patientDto.setId(patient.getId());
                patientDto.setName(patient.getName());
                patientDto.setDateOfBirth(patient.getDateOfBirth());
                patientDto.setMobileNumber(patient.getMobileNumber());
                patientDto.setGender(patient.getGender());
                patientDto.setEmail(patient.getEmail());
                patientDto.setStatus(patient.getStatus());
                vitalDto.setPatient(patientDto);
            }
        }
        return vitalDto;
    }
}

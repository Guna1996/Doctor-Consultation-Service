/**
 * <p>
 * This is the base package for all the mapper classes
 * which is for DoctorMapper, PatientMapper and ClinicMapper
 * classes
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.mapper;

import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.model.Patient;

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
     * @param patientDto {@link PatientDto} contains patient details
     * @return {@link Patient}
     */
    public static Patient fromDto(PatientDto patientDto) {
        Patient patient = new Patient();
        if (null != patientDto) {
            patient.setId(patientDto.getId());
            patient.setName(patientDto.getName());
            patient.setDateOfBirth(patientDto.getDateOfBirth());
            patient.setGender(patientDto.getGender());
            patient.setMobileNumber(Long.parseLong(patientDto.getMobileNumber()));
            patient.setEmail(patientDto.getEmail());
            patient.setStatus(patientDto.getStatus());
        }
        return patient;
    }

    /**
     * <p>
     * This method is used to convert Patient to
     * PatientDto
     * </p>
     *
     * @param patient {@link Patient} contains patient details
     * @return {@link PatientDto}
     */
    public static PatientDto toDto(Patient patient) {
        PatientDto patientDto = new PatientDto();
        if (null != patient) {
            patientDto.setId(patient.getId());
            patientDto.setName(patient.getName());
            patientDto.setDateOfBirth(patient.getDateOfBirth());
            patientDto.setGender(patient.getGender());
            patientDto.setMobileNumber(Long.toString(patient.getMobileNumber()));
            patientDto.setEmail(patient.getEmail());
            patientDto.setStatus(patient.getStatus());
        }
        return patientDto;
    }
}

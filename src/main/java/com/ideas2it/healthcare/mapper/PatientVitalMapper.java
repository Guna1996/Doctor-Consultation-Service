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
import com.ideas2it.healthcare.dto.PatientVitalDto;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.model.Patient;
import com.ideas2it.healthcare.model.PatientVital;
import com.ideas2it.healthcare.util.DateUtil;
import com.ideas2it.healthcare.util.VitalUtil;

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
public class PatientVitalMapper {

    /**
     * <p>
     * This method is used to convert VitalDto to
     * Vital model
     * </p>
     *
     * @param vitalsDto {@link PatientVitalDto} contains vitals details
     * @return {@link PatientVital}
     */
    public static PatientVital fromDto(PatientVitalDto vitalsDto) {
        PatientVital patientVital = new PatientVital();
        if (null != vitalsDto) {
            patientVital.setId(vitalsDto.getId());
            patientVital.setHeight(vitalsDto.getHeight());
            patientVital.setWeight(vitalsDto.getWeight());
            patientVital.setPulse(vitalsDto.getPulse());
            patientVital.setSystolic(vitalsDto.getSystolic());
            patientVital.setDiastolic(vitalsDto.getDiastolic());
            patientVital.setSugarLevel(vitalsDto.getSugarLevel());
            patientVital.setStatus(vitalsDto.getStatus());
            patientVital.setCreatedAt(vitalsDto.getCreatedAt());
            patientVital.setBpRiskLevel(VitalUtil.getBPRiskLevel(vitalsDto.getSystolic(),
                    vitalsDto.getDiastolic()));
            DoctorDto doctorDto = vitalsDto.getDoctor();
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
            PatientDto patientDto = vitalsDto.getPatient();
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
        }
        return patientVital;
    }

    /**
     * <p>
     * This method is used to convert Vital to
     * VitalDto
     * </p>
     *
     * @param patientVital {@link PatientVital} contains vitals details
     * @return {@link PatientVitalDto}
     */
    public static PatientVitalDto toDto(PatientVital patientVital) {
        PatientVitalDto vitalsDto = new PatientVitalDto();
        if (null != patientVital) {
            vitalsDto.setId(patientVital.getId());
            vitalsDto.setHeight(patientVital.getHeight());
            vitalsDto.setWeight(patientVital.getWeight());
            vitalsDto.setPulse(patientVital.getPulse());
            vitalsDto.setSystolic(patientVital.getSystolic());
            vitalsDto.setDiastolic(patientVital.getDiastolic());
            vitalsDto.setSugarLevel(patientVital.getSugarLevel());
            vitalsDto.setStatus(patientVital.getStatus());
            vitalsDto.setBpRiskLevel(patientVital.getBpRiskLevel());
            vitalsDto.setCreatedAt(patientVital.getCreatedAt());
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
                if(null != doctor.getDateOfRegistration()) {
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
                vitalsDto.setDoctor(doctorDto);
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
                vitalsDto.setPatient(patientDto);
            }
        }
        return vitalsDto;
    }
}

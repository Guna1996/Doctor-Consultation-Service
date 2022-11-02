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
import com.ideas2it.healthcare.dto.VitalsDto;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.model.Patient;
import com.ideas2it.healthcare.model.Vital;
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
public class VitalsMapper {

    /**
     * <p>
     * This method is used to convert VitalDto to
     * Vital model
     * </p>
     *
     * @param vitalsDto {@link VitalsDto} contains vitals details
     * @return {@link Vital}
     */
    public static Vital fromDto(VitalsDto vitalsDto) {
        Vital vital = new Vital();
        if (null != vitalsDto) {
            vital.setId(vitalsDto.getId());
            vital.setHeight(vitalsDto.getHeight());
            vital.setWeight(vitalsDto.getWeight());
            vital.setPulse(vitalsDto.getPulse());
            vital.setSystolic(vitalsDto.getSystolic());
            vital.setDiastolic(vitalsDto.getDiastolic());
            vital.setSugarLevel(vitalsDto.getSugarLevel());
            vital.setStatus(vitalsDto.getStatus());
            vital.setCreatedAt(vitalsDto.getCreatedAt());
            vital.setBpRiskLevel(VitalUtil.getBPRiskLevel(vitalsDto.getSystolic(), vitalsDto.getDiastolic()));
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
                vital.setDoctor(doctor);
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
     * @param vital {@link Vital} contains vitals details
     * @return {@link VitalsDto}
     */
    public static VitalsDto toDto(Vital vital) {
        VitalsDto vitalsDto = new VitalsDto();
        if (null != vital) {
            vitalsDto.setId(vital.getId());
            vitalsDto.setHeight(vital.getHeight());
            vitalsDto.setWeight(vital.getWeight());
            vitalsDto.setPulse(vital.getPulse());
            vitalsDto.setSystolic(vital.getSystolic());
            vitalsDto.setDiastolic(vital.getDiastolic());
            vitalsDto.setSugarLevel(vital.getSugarLevel());
            vitalsDto.setStatus(vital.getStatus());
            vitalsDto.setBpRiskLevel(vital.getBpRiskLevel());
            vitalsDto.setCreatedAt(vital.getCreatedAt());
            Doctor doctor = vital.getDoctor();
            if (null != doctor) {
                DoctorDto doctorDto = new DoctorDto();
                doctorDto.setId(doctor.getId());
                doctorDto.setName(doctor.getName());
                doctorDto.setGender(doctor.getGender());
                doctorDto.setDateOfBirth(doctor.getDateOfBirth());
                doctorDto.setAge(DateUtil.getDifferenceInYears(doctor.getDateOfBirth()));
                doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
                doctorDto.setExperience(DateUtil.getDifferenceInYears(doctor.getDateOfRegistration()));
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
            Patient patient = vital.getPatient();
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

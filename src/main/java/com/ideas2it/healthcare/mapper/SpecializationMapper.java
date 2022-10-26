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
import com.ideas2it.healthcare.dto.SpecializationDto;
import com.ideas2it.healthcare.model.Doctor;
import com.ideas2it.healthcare.model.Specialization;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * SpecializationMapper is used convert Specialization object to
 * SpecializationDto and SpecializationDto into Specialization
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public class SpecializationMapper {

    /**
     * <p>
     * This method is used to convert SpecializationDto to
     * Specialization model
     * </p>
     *
     * @return {@link Specialization}
     */
    public static Specialization fromDto(SpecializationDto specializationDto) {

        Specialization specialization = new Specialization();
        if (specializationDto != null) {
            specialization.setId(specializationDto.getId());
            specialization.setName(specializationDto.getName());
            specialization.setStatus(specializationDto.getStatus());
            /*Set<DoctorDto> doctorsDto = specializationDto.getDoctors();
            if (doctorsDto != null) {
                Set<Doctor> doctors = new HashSet<>();
                doctorsDto.forEach(doctorDto -> {
                    Doctor doctor = new Doctor();
                    doctor.setId(doctorDto.getId());
                    doctor.setName(doctorDto.getName());
                    doctor.setGender(doctorDto.getGender());
                    doctor.setDateOfBirth(doctorDto.getDateOfBirth());
                    doctor.setConsultationFee(doctorDto.getConsultationFee());
                    doctor.setQualification(doctorDto.getQualification());
                    doctor.setMobileNumber(doctorDto.getMobileNumber());
                    doctor.setDateOfRegistration(doctorDto.getDateOfRegistration());
                    doctor.setCity(doctorDto.getCity());
                    doctor.setStatus(doctorDto.getStatus());
                    doctors.add(doctor);
                });
                specialization.setDoctors(doctors);

            }*/
        }
        return specialization;
    }

    /**
     * <p>
     * This method is used to convert Specialization to
     * SpecializationDto
     * </p>
     *
     * @return {@link SpecializationDto}
     */
    public static SpecializationDto toDto(Specialization specialization) {

        SpecializationDto specializationDto = new SpecializationDto();
        if (specialization != null) {
            specializationDto.setId(specialization.getId());
            specializationDto.setName(specialization.getName());
            specializationDto.setStatus(specialization.getStatus());
            /*Set<Doctor> doctors = specialization.getDoctors();
            if (doctors != null) {
                Set<DoctorDto> doctorsDto = new HashSet<>();
                doctors.forEach(doctor -> {
                    DoctorDto doctorDto = new DoctorDto();
                    doctorDto.setId(doctor.getId());
                    doctorDto.setName(doctor.getName());
                    doctorDto.setGender(doctor.getGender());
                    doctorDto.setConsultationFee(doctor.getConsultationFee());
                    doctorDto.setDateOfBirth(doctor.getDateOfBirth());
                    doctorDto.setQualification(doctor.getQualification());
                    doctorDto.setMobileNumber(doctor.getMobileNumber());
                    doctorDto.setDateOfRegistration(doctor.getDateOfRegistration());
                    doctorDto.setCity(doctor.getCity());
                    doctorDto.setStatus(doctor.getStatus());
                    doctorsDto.add(doctorDto);
                });
                specializationDto.setDoctors(doctorsDto);
            }*/
        }
        return specializationDto;
    }
}

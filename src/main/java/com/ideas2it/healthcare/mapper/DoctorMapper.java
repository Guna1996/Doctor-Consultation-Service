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
import com.ideas2it.healthcare.util.DateUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * DoctorMapper is used convert Doctor object to
 * DoctorDto and DoctorDto into Doctor
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
public class DoctorMapper {

    /**
     * <p>
     * This method is used to convert DoctorDto to
     * Doctor model
     * </p>
     *
     * @param doctorDto {@link DoctorDto} contains doctor details
     * @return {@link Doctor}
     */
    public static Doctor fromDto(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        if (null != doctorDto) {
            doctor.setId(doctorDto.getId());
            doctor.setName(doctorDto.getName());
            doctor.setDateOfBirth(doctorDto.getDateOfBirth());
            doctor.setGender(doctorDto.getGender());
            doctor.setQualification(doctorDto.getQualification());
            Set<SpecializationDto> specializationsDto = doctorDto.getSpecializations();
            if (null != specializationsDto) {
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
            doctor.setMobileNumber(Long.parseLong(doctorDto.getMobileNumber()));
            doctor.setCity(doctorDto.getCity());
            doctor.setStatus(doctorDto.getStatus());
            doctor.setConsultationFee(doctorDto.getConsultationFee());
        }
        return doctor;
    }

    /**
     * <p>
     * This method is used to convert Doctor model to
     * DoctorDto
     * </p>
     *
     * @param doctor {@link Doctor} contains doctor details
     * @return {@link DoctorDto}
     */
    public static DoctorDto toDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        if (null != doctor) {
            doctorDto.setId(doctor.getId());
            doctorDto.setName(doctor.getName());
            doctorDto.setAge(DateUtil.getDifferenceBetweenDates(doctor.getDateOfBirth()));
            doctorDto.setGender(doctor.getGender());
            doctorDto.setQualification(doctor.getQualification());
            doctorDto.setConsultationFee(doctor.getConsultationFee());
            Set<Specialization> specializations = doctor.getSpecializations();
            if (null != specializations) {
                Set<SpecializationDto> specializationsDto = new HashSet<>();
                specializations.forEach(specialization -> {
                    SpecializationDto specializationDto = new SpecializationDto();
                    specializationDto.setId(specialization.getId());
                    specializationDto.setName(specialization.getName());
                    specializationDto.setStatus(specialization.getStatus());
                    specializationsDto.add(specializationDto);
                });
                doctorDto.setSpecializations(specializationsDto);
            }
            doctorDto.setExperience(DateUtil.getDifferenceBetweenDates(doctor.getDateOfRegistration()));
            doctorDto.setMobileNumber(Long.toString(doctor.getMobileNumber()));
            doctorDto.setCity(doctor.getCity());
            doctorDto.setStatus(doctor.getStatus());
        }
        return doctorDto;
    }
}

/**
 * <p>
 * This is the base package for all the mapper classes
 * which is for Doctor mapper, PatientMapper and ClinicMapper
 * classes.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.mapper;

import com.ideas2it.healthcare.dto.SpecializationDto;
import com.ideas2it.healthcare.model.Specialization;

/**
 * <p>
 * Specialization mapper class is used convert Specialization object into
 * Specialization dto and also used to convert Specialization dto into
 * Specialization model.
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public class SpecializationMapper {

    /**
     * <p>
     * This method is used to convert Specialization dto into
     * Specialization model.
     * </p>
     *
     * @param specializationDto {@link SpecializationDto} contains specialization details
     * @return {@link Specialization}
     */
    public static Specialization fromDto(SpecializationDto specializationDto) {
        Specialization specialization = new Specialization();
        if (null != specializationDto) {
            specialization.setId(specializationDto.getId());
            specialization.setName(specializationDto.getName());
            specialization.setStatus(specializationDto.getStatus());
            specialization.setCreatedAt(specialization.getCreatedAt());
            specialization.setUpdatedAt(specialization.getUpdatedAt());
        }
        return specialization;
    }

    /**
     * <p>
     * This method is used to convert Specialization model into
     * Specialization dto.
     * </p>
     *
     * @param specialization {@link Specialization} contains specialization details
     * @return {@link SpecializationDto}
     */
    public static SpecializationDto toDto(Specialization specialization) {
        SpecializationDto specializationDto = new SpecializationDto();
        if (null != specialization) {
            specializationDto.setId(specialization.getId());
            specializationDto.setName(specialization.getName());
            specializationDto.setStatus(specialization.getStatus());
            specializationDto.setCreatedAt(specialization.getCreatedAt());
            specializationDto.setUpdatedAt(specialization.getUpdatedAt());
        }
        return specializationDto;
    }
}

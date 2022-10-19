/**
 * <p>
 * This is the base package for all the service interfaces
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.SpecializationDto;

import java.util.List;

/**
 * <p>
 * This SpecializationService interface is a service interface and this
 * interface is used to contain the body of SpecializationServiceImpl
 * class's methods
 * </p>
 *
 * @author  Mohamed Jubair
 *
 * @version 1
 *
 * @since   2022-10-10
 */
public interface SpecializationService {

    /**
     * <p>
     * This method is used to create and Update Specialization's record by
     * getting SpecializationDto as an input and convert it in to
     * Specialization model with the help of mapper class
     * </p>
     *
     * @param specializationDto {@link SpecializationDto}
     *
     */
    SpecializationDto saveOrUpdateSpecialization(SpecializationDto specializationDto);

    /**
     * <p>
     * This method is used to get all Specialization's record by
     * getting all Specializations from database and convert it in to
     * SpecializationDto with the help of mapper class
     * </p>
     *
     * @return {@link List <SpecializationDto>}
     */
    List<SpecializationDto> getAllSpecializations();

    /**
     * <p>
     * This method is used to get the Specialization by id and
     * convert it into SpecializationDto
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link SpecializationDto}
     */
    SpecializationDto getSpecializationById(int id);


    /**
     * <p>
     * This method is used to delete the Specialization by id
     * </p>
     *
     * @param id {@link int}
     *
     *@return {@link String}
     */
    String deleteSpecializationById(int id);
}

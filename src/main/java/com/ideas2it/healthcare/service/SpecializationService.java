/**
 * <p>
 * This package contains interfaces of Doctor clinic service,
 * Patient service, Doctor service, Clinic service,
 * Appointment service, Feedback service, Specialization service,
 * Timeslot service, Patient vital service.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.SpecializationDto;

import java.util.List;

/**
 * <p>
 * SpecializationService interface consists of abstract methods which is used
 * for performing crud operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public interface SpecializationService {

    /**
     * <p>
     * This method is used to create and update Specialization's record by
     * getting SpecializationDto as an input and convert it into
     * Specialization model with the help of mapper class
     * </p>
     *
     * @param specializationDto {@link SpecializationDto}
     * @return {@link String}
     */
    String addOrUpdateSpecialization(SpecializationDto specializationDto);

    /**
     * <p>
     * This method is used to get all Specialization's record by
     * getting all Specializations from database and convert it into
     * SpecializationDto with the help of mapper class
     * </p>
     *
     * @return {@link List<SpecializationDto>}
     */
    List<SpecializationDto> getAllSpecializations(Integer pageNumber, Integer totalRows);

    /**
     * <p>
     * This method is used to get the Specialization from the
     * database which is in active status in the database of specialization
     * table by specialization id
     * </p>
     *
     * @param id {@link int}
     * @return {@link SpecializationDto}
     */
    SpecializationDto getSpecializationById(Integer id);

    /**
     * <p>
     * This method is used to delete the Specialization by id
     * and before that once the specialization is present in the
     * table we can remove otherwise if we try to remove it will
     * showing error message for this action.
     * </p>
     *
     * @param id {@link Integer}
     * @return {@link String}
     */
    String removeSpecializationById(Integer id);

    /**
     * <p>
     * This method is used to get the count of Specialization's
     * this method will considered only active status specialization
     * for count and will return the count of specialization as an integer
     * </p>
     *
     * @return {@link Integer}
     */
    Integer getSpecializationsCount();
}

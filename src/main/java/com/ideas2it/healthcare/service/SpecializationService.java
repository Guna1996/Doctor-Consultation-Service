/**
 * <p>
 * This package contains interfaces of DoctorClinicService,
 * PatientService, DoctorService, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalServiceImpl.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.SpecializationDto;

import java.util.List;

/**
 * <p>
 * SpecializationService interface consists of abstract methods which is used
 * for performing CRUD operation. it is used to transfer objects between
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
     * This method is used to create or Update Specialization's record by
     * getting SpecializationDto as an input and convert it into
     * Specialization model with the help of mapper class
     * </p>
     *
     * @param specializationDto {@link SpecializationDto}
     */
    SpecializationDto saveOrUpdateSpecialization(SpecializationDto specializationDto);

    /**
     * <p>
     * This method is used to get all Specialization's record by
     * getting all Specializations from database and convert it into
     * SpecializationDto with the help of mapper class
     * </p>
     *
     * @return {@link List <SpecializationDto>}
     */
    List<SpecializationDto> getAllSpecializations(int pageNumber, int totalRows);

    /**
     * <p>
     * This method is used to get the Specialization from the
     * database by specialization id
     * </p>
     *
     * @param id {@link int}
     * @return {@link SpecializationDto}
     */
    SpecializationDto getSpecializationById(int id);


    /**
     * <p>
     * This method is used to delete the Specialization by id
     * by making status as inactive
     * </p>
     *
     * @param id {@link int}
     * @return {@link String}
     */
    String deleteSpecializationById(int id);
}

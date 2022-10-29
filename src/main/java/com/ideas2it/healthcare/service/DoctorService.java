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

import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.dto.FeedbackDto;

import java.util.List;

/**
 * <p>
 * DoctorService interface consists of abstract methods which is used
 * for performing CRUD operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public interface DoctorService {

    /**
     * <p>
     * This method is used to create and Update Doctor's record by
     * getting DoctorDto as an input and convert it in to
     * Doctor model with the help of mapper class
     * </p>
     *
     * @param doctorDto {@link DoctorDto}
     */
    DoctorDto saveOrUpdateDoctor(DoctorDto doctorDto);

    /**
     * <p>
     * This method is used to get all Doctor's record by
     * getting all Doctors from database and convert it in to
     * DoctorDto with the help of mapper class
     * </p>
     *
     * @return {@link List<DoctorDto>}
     */
    List<DoctorDto> getAllDoctors(int pageNumber, int totalRows);

    /**
     * <p>
     * This method is used to get the Doctor by id and
     * convert it into DoctorDto
     * </p>
     *
     * @param id {@link int}
     * @return {@link DoctorDto}
     */
    DoctorDto getDoctorById(int id);

    /**
     * <p>
     * This method is used to delete the Doctor by id
     * by making status as inactive
     * </p>
     *
     * @param id {@link int}
     * @return {@link String}
     */
    String deleteDoctorById(int id);
}

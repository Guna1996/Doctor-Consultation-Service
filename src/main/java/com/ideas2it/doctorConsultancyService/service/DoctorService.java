/**
 * <p>
 * This is the base package for all the service interfaces
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.service;

import com.ideas2it.doctorConsultancyService.dto.DoctorDto;

import java.util.List;

/**
 * <p>
 * This DoctorService interface is a service interface and this
 * interface is used to contain the body of DoctorServiceImpl
 * class's methods
 * </p>
 *
 * @author  Mohamed Jubair
 *
 * @version 1
 *
 * @since   2022-10-10
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
     *
     */
    DoctorDto saveOrUpdate(DoctorDto doctorDto);

    /**
     * <p>
     * This method is used to get all Doctor's record by
     * getting all Doctors from database and convert it in to
     * DoctorDto with the help of mapper class
     * </p>
     *
     * @return {@link List<DoctorDto>}
     */
    List<DoctorDto> getAllDoctors();

    /**
     * <p>
     * This method is used to get the Doctor by id and
     * convert it into DoctorDto
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link DoctorDto}
     */
    DoctorDto getDoctorById(int id);


    /**
     * <p>
     * This method is used to delete the Doctor by id
     * </p>
     *
     * @param id {@link int}
     *
     *@return {@link String}
     */
    String deleteById(int id);
}

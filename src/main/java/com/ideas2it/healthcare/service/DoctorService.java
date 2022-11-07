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

import com.ideas2it.healthcare.dto.DoctorDto;

import java.util.List;

/**
 * <p>
 * Doctor service interface consists of abstract methods which is used
 * for performing crud operation. it is used to transfer objects between
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
     * This method is used to create Doctor's record by
     * getting Doctor dto as an input and convert it in to
     * Doctor model with the help of mapper class
     * </p>
     *
     * @param doctorDto {@link DoctorDto} contains details of doctor
     * @return {@link String}
     */
    String addDoctor(DoctorDto doctorDto);

    /**
     * <p>
     * This method is used to get all Doctor's record by
     * getting all Doctors from database and convert it in to
     * DoctorDto with the help of mapper class using pagination
     * which can get only the required number of rows.
     * </p>
     *
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is required number of rows to be displayed
     * @return {@link List<DoctorDto>}
     */
    List<DoctorDto> getAllDoctors(Integer pageNumber, Integer totalRows);

    /**
     * <p>
     * This method is used to get the Doctor  by id and
     * convert it into DoctorDto
     * </p>
     *
     * @param id {@link Integer} is id of doctor
     * @return {@link DoctorDto}
     */
    DoctorDto getDoctorById(Integer id);

    /**
     * <p>
     * This method is used to update Doctor's record by
     * getting DoctorDto as an input and convert it in to
     * Doctor model with the help of mapper class
     * </p>
     *
     * @param doctorDto {@link DoctorDto} contains details of doctor
     * @return {@link String}
     */
    String updateDoctor(DoctorDto doctorDto);

    /**
     * <p>
     * This method is used to delete the Doctor by id
     * by making status as inactive
     * </p>
     *
     * @param id {@link Integer} is id of doctor
     * @return {@link String}
     */
    String removeDoctorById(Integer id);

    /**
     * <p>
     * This method is used to get the count of Doctor's record
     * with the status
     * </p>
     *
     * @return {@link Integer}
     */
    Integer getDoctorsCount();
}

/**
 * <p>
 * This package contains interfaces are Doctor clinic service,
 * Patient service, Doctor service, Clinic service,
 * Appointment service, Feedback service, Specialization service,
 * Timeslot service, Patient vital Service.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.ClinicDto;

import java.util.List;

/**
 * <p>
 * Clinic service interface consists of abstract methods which is used
 * for performing crud operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
public interface ClinicService {

    /**
     * <p>
     * This method is used to add clinic details such as clinic name,
     * street name, pin code, city name, state name, contact number, etc
     * into data base by getting details.
     * </p>
     *
     * @param clinicDto {@link ClinicDto} is clinic details
     * @return {@link String}
     */
    String addClinic(ClinicDto clinicDto);

    /**
     * <p>
     * This method is used to get all the active Clinic details
     * from the database using pagination which can get
     * only the required number of rows.
     * </p>
     *
     * @param pageNumber {@link Integer} is Page number
     * @param totalRows  {@link Integer} is number of rows required
     * @return {@link List<ClinicDto>}
     */
    List<ClinicDto> getClinics(Integer pageNumber, Integer totalRows);

    /**
     * <p>
     * This method is used to get active clinic
     * details from the database by getting the id and status
     * from the admin to display the clinic details.
     * </p>
     *
     * @param id {@link Integer} id of the clinic
     * @return {@link ClinicDto}
     */
    ClinicDto getClinicById(Integer id);

    /**
     * <p>
     * This method is used to update clinic all details into the
     * database such as clinic name, street name, city, state,
     * contact number to update into database.
     * </p>
     *
     * @param clinicDto {@link ClinicDto} clinic detail
     * @return {@link String}
     */
    String updateClinic(ClinicDto clinicDto);

    /**
     * <p>
     * This method is used to delete clinic details
     * from the database which means the action is
     * inactive the status column of clinic table
     * by getting clinic id
     * </p>
     *
     * @param id {@link Integer} id of the clinic
     * @return {@link String}
     */
    String removeClinicById(Integer id);

    /**
     * <p>
     * This method is used to get the count of Clinics
     * if the clinic status is active it will count otherwise
     * this method will not get count, finally it will
     * return all the clinics count as integer
     * </p>
     *
     * @return {@link Integer}
     */
    Integer getCountOfClinics();
}

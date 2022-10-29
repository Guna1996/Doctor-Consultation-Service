/**
 * <p>
 * This package contains interfaces are DoctorClinicService,
 * PatientService, DoctorService, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalServiceImpl.
 * </p>
 * <p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.dto.DoctorClinicDto;

import java.util.List;

/**
 * <p>
 * ClinicService interface consists of abstract methods which is used
 * for performing CRUD operation. it is used to transfer objects between
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
     * This abstract method is used to add appointment details
     * into data base by getting details from the
     * user
     * </p>
     *
     * @param clinicDto - clinic details
     * @return ClinicDto
     */
    ClinicDto addClinic(ClinicDto clinicDto);

    /**
     * <p>
     * This method is used to get all the Clinic details
     * from the database
     * </p>
     *
     * @return list<ClinicDto>
     */
    List<ClinicDto> getClinics(int pageNumber, int totalRows);

    /**
     * <p>
     * This abstract method is used to get client
     * details from the database by getting the id and status
     * from the user to display
     * </p>
     *
     * @param id - id of the clinic
     * @return ClinicDto
     */
    ClinicDto getClinicById(int id);

    /**
     * <p>
     * This method is used to update clinic
     * all details into the database
     * </p>
     *
     * @param clinicDto - clinic detail
     * @return ClinicDto
     */
    ClinicDto updateClinic(ClinicDto clinicDto);

    /**
     * <p>
     * This method is used to delete clinic details
     * by getting id from the user
     * </p>
     *
     * @param id - id of the clinic
     * @return String
     */
    String deleteClinicById(int id);
}

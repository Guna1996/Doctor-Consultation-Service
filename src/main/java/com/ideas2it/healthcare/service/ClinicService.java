/**
 * <p>
 * This package contains interfaces are DoctorClinicService,
 * PatientService, DoctorService, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalServiceImpl.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.ClinicDto;

import java.util.List;

/**
 * <p>
 * ClinicService interface is used for converting dto into entity
 * class and also it helps to passing objects between controller
 * to repository for CRUD operations
 * </p>
 *
 * @author  Gunaseelan K
 *
 * @version 1
 *
 * @since   2022-10-18
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
     *
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
     *  This abstract method is used to get client
     *  details from the database by getting the id and status
     *  from the user to dispaly
     * </p>
     *
     * @param id - id of the clinic
     *
     * @return ClinicDto
     */
    ClinicDto getClinicById(int id);

    /**
     * <p>
     * This method is used to update clinic
     * all details into the database
     * </p>
     * @param clinicDto - clinic detail
     *
     * @return ClinicDto
     */
    ClinicDto updateClinic(ClinicDto clinicDto);

    /**
     * <p>
     * This method is used to delete clinic details
     * by getting id from the user
     * </p>
     * @param id - id of the clinic
     *
     * @return String
     */
    String deleteClinicById(int id);

    /**
     * <p>
     * This isClinicAvailable abstract method is
     * used to check wheather a clinic is available or not
     * on the clinic table by using getting id from the
     * clinic
     * </p>
     *
     * @param id - id of the clinic
     *
     * @return boolean
     */
    boolean isClinicAvailable(int id);
}

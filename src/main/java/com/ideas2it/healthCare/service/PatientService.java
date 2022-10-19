/**
 * <p>
 * This package contains interfaces are DoctorClinicService,
 * PatientService, DoctorService, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalsServiceImpl.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.PatientDto;

import java.util.List;

/**
 * <p>
 * PatientService interface is used for converting dto into entity
 * class and also it helps to passing objects between controller
 * to repository for CRUD operations
 * </p>
 *
 * @author  Ramachandran
 *
 * @version 1
 *
 * @since   2022-07-18
 */

public interface PatientService {

    /**
     * <p>
     * This abstract method is used to add patient details
     * into data base by getting details from the
     * user
     * </p>
     *
     * @param patientDto - patient details
     *
     * @return patientDto
     */
    PatientDto addPatient(PatientDto patientDto);

    /**
     * <p>
     *  This abstract method is used to get patient
     *  details from the database by getting the id and status
     *  from the user to dispaly
     * </p>
     *
     * @param id - id of the patient
     *
     * @return PatientDto
     */
    PatientDto getPatientById(Integer id);

    /**
     * <p>
     * This method is used to update Patient
     * all details into the database
     * </p>
     * @param patientDto - patient detail
     *
     * @return PatientDto
     */
    PatientDto updatePatient(PatientDto patientDto);

    /**
     * <p>
     * This method is used to delete patient details
     * by getting id from the user
     * </p>
     * @param id - id of the patient
     *
     * @return String
     */
    String deletePatient(Integer id);

    /**
     * <p>
     * This method is used to get all the Patient details
     * from the database
     * </p>
     *
     * @return list<PatientDto>
     */
    List<PatientDto> getPatients(int pageNumber, int totalRows);

    /**
     * <p>
     * This isPatientAvailable abstract method is
     * used to check wheather a patient is available or not
     * on the patient table by using getting id from the
     * patient
     * </p>
     *
     * @param id - id of the patient
     *
     * @return boolean
     */
    boolean isPatientAvailable(Integer id);
}

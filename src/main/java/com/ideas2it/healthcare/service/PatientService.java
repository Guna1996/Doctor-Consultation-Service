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

import com.ideas2it.healthcare.dto.PatientDto;

/**
 * <p>
 * PatientService interface consists of abstract methods which is used
 * for performing CRUD operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */

public interface PatientService {

    /**
     * <p>
     * This abstract method is used to add patient details
     * into data base by getting details from the user
     * </p>
     *
     * @param patientDto - patient details
     * @return patientDto
     */
    PatientDto addPatient(PatientDto patientDto);

    /**
     * <p>
     * This abstract method is used to get patient
     * details from the database by patient id
     * </p>
     *
     * @param id - id of the patient
     * @return PatientDto
     */
    PatientDto getPatientById(Integer id);

    /**
     * <p>
     * This method is used to update Patient
     * all details into the database
     * </p>
     *
     * @param patientDto - patient detail
     * @return PatientDto
     */
    PatientDto updatePatient(PatientDto patientDto);
}

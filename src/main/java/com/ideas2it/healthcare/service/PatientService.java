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

import com.ideas2it.healthcare.dto.PatientDto;

/**
 * <p>
 * PatientService interface consists of abstract methods which is used
 * for performing crud operation. it is used to transfer objects between
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
     * This method is used to add patient details
     * into data base by getting details from the user
     * </p>
     *
     * @param patientDto {@link PatientDto} contains patient details
     * @return {@link PatientDto}
     */
    PatientDto addPatient(PatientDto patientDto);

    /**
     * <p>
     * This method is used to get patient
     * details from the database by patient id
     * </p>
     *
     * @param id {@link Integer} id of the patient
     * @return {@link PatientDto}
     */
    PatientDto getPatientById(Integer id);

    /**
     * <p>
     * This method is used to update Patient
     * all details into the database
     * </p>
     *
     * @param patientDto {@link PatientDto} patient detail
     * @return {@link PatientDto}
     */
    PatientDto updatePatient(PatientDto patientDto);
}

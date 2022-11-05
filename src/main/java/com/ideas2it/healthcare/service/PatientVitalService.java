/**
 * <p>
 * This package contains interfaces of Doctor clinic service,
 * Patient service, Doctor service, Clinic service,
 * Appointment service, Feedback service, Specialization service,
 * Timeslot service, Patient vital service .
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.PatientVitalDto;

import java.util.List;

/**
 * <p>
 * Patient vital service interface consists of abstract methods which is used
 * for performing crud operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Bala Ashwanth
 * @since 2022-10-10
 */
public interface PatientVitalService {

    /**
     * <p>
     * This abstract method is used to add vital details
     * into the database
     * </p>
     *
     * @param vitalsDto {@link PatientVitalDto}
     * @return {@link PatientVitalDto}
     */
    PatientVitalDto addVitals(PatientVitalDto vitalsDto);

    /**
     * <p>
     * This abstract method is used to get all vitals
     * from the database using patient id
     * </p>
     *
     * @param patientId  {@link Integer} is id of patient
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is required number of rows to be displayed
     * @return {@link List<PatientVitalDto>}
     */
    List<PatientVitalDto> getVitalsByPatientId(Integer patientId, Integer pageNumber, Integer totalRows);

    /**
     * <p>
     * This abstract method is used to get the count of vitals
     * from the database using patient id
     * </p>
     *
     * @param patientId  {@link Integer} is id of patient
     * @return {@link Integer}
     */
    Integer countOfVitalsByPatientId(Integer patientId);
}

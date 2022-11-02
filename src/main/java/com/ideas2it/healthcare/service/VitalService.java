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

import com.ideas2it.healthcare.dto.PatientVitalDto;

import java.util.List;

/**
 * <p>
 * VitalsService interface consists of abstract methods which is used
 * for performing CRUD operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Bala Ashwanth
 * @since 2022-10-10
 */
public interface VitalService {

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
     * @param patientId  {@link int} is id of patient
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is required number of rows to be displayed
     * @return {@link List<  PatientVitalDto  >}
     */
    List<PatientVitalDto> getVitalsByPatientId(Integer patientId, Integer pageNumber, Integer totalRows);

    Long getTotalPages();

    void setTotalPages(Long totalPages);
}

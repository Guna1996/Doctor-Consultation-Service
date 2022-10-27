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

import com.ideas2it.healthcare.dto.VitalDto;

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
     * @param vitalsDto {@link VitalDto}
     * @return {@link VitalDto}
     */
    VitalDto addVitals(VitalDto vitalsDto);

    /**
     * <p>
     * This abstract method is used to get all vitals
     * from the database using patient id
     * </p>
     *
     * @param patientId  {@link int}
     * @param pageNumber {@link int}
     * @param totalRows  {@link int}
     * @return {@link List<VitalDto>}
     */
    List<VitalDto> getVitalsByPatientId(int patientId, int pageNumber, int totalRows);
}

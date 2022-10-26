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

import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.model.Vital;

import java.util.List;

/**
 * <p>
 * PatientService interface is used for converting dto into entity
 * class and also it helps to passing objects between controller
 * to repository for CRUD operations
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
     * into data base by getting details from the
     * user
     * </p>
     *
     * @param patientDto - patient details
     * @return patientDto
     */
    PatientDto addPatient(PatientDto patientDto);

    /**
     * <p>
     * This abstract method is used to get patient
     * details from the database by getting the id and status
     * from the user to display
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

    /**
     * <p>
     * This isPatientAvailable abstract method is
     * used to check whether a patient is available or not
     * on the patient table by using getting id from the
     * patient
     * </p>
     *
     * @param id - id of the patient
     * @return boolean
     */
    boolean isPatientAvailable(Integer id);

    List<VitalDto> getVitalsByPatientId(int patientId, int pageNumber, int totalRows);
}

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

import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.model.Vital;

import java.util.List;

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

    /**
     * <p>
     * This isPatientAvailable abstract method is
     * used to check whether a patient is available or not
     * on the patient table by  getting id from the
     * patient
     * </p>
     *
     * @param id - id of the patient
     * @return boolean
     */
    boolean isPatientAvailable(Integer id);

    /**
     * <p>
     * This method is used to get vitals of a particular patient
     * by patient id
     * </p>
     *
     * @param patientId is id of patient
     * @param pageNumber is page number of vitals
     * @param totalRows is required number of rows to be displayed
     * @return List<VitalDto>
     */
    List<VitalDto> getVitalsByPatientId(int patientId, int pageNumber, int totalRows);

    /**
     * <p>
     * This method is used to get appointments of a particular patient
     * by patient id
     * </p>
     *
     * @param patientId is id of patient
     * @param pageNumber is page number of appointments
     * @param totalRows is required number of rows to be displayed
     * @return List<AppointmentDto>
     */
    List<AppointmentDto> getAppointmentsByPatientId(int patientId, int pageNumber, int totalRows);
}

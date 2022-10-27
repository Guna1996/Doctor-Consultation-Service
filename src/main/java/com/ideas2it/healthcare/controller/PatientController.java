/**
 * <p>
 * This is the package contains classes are DoctorClinicController,
 * PatientController,DoctorController,ClinicController,
 * AppointmentController,FeedbackController,SpecializationController,
 * TimeslotController,VitalController
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * This PatientController class is used to get and update
 * information from the patient and also used to get
 * their vitals and appointments.
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * <p>
     * This addPatient method is used to add patient details
     * by getting inputs from the patient.
     * </p>
     *
     * @param patientDto is patient detail
     * @return String
     */
    @PostMapping
    public PatientDto addPatient(@RequestBody PatientDto patientDto) {
        return patientService.addPatient(patientDto);
    }

    /**
     * <p>
     * This getPatientById method is used to get
     * a details of a patient.
     * </p>
     *
     * @param id is patient id
     * @return PatientDto
     */
    @GetMapping(Constants.ID)
    public PatientDto getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    /**
     * <p>
     * This updatePatient method is used to update patient
     * detail after validating it.
     * </p>
     *
     * @param patientDto is detail of patient
     * @return PatientDto
     */
    @PutMapping
    public PatientDto updatePatient(@RequestBody PatientDto patientDto) {
        return  patientService.updatePatient(patientDto);
    }

    /**
     * <p>
     * This getVitalByPatientId method is used to
     * get vitals of patient.
     * </p>
     *
     * @param patientId
     * @param pageNumber
     * @param totalRows
     * @return List<VitalDto>
     */
    @GetMapping(Constants.VITAL_PATIENT_ID)
    public List<VitalDto> getVitalByPatientId(@PathVariable(name = "patientId") int patientId,
                                              @PathVariable(name = "pageNumber") int pageNumber,
                                              @PathVariable(name = "totalRows") int totalRows) {
        return patientService.getVitalsByPatientId(patientId, pageNumber, totalRows);
    }

    /**
     * <p>
     * This getAppointmentsByPatientId method is used
     * to get list of appointments of a patient.
     * </p>
     *
     * @param patientId
     * @param pageNumber
     * @param totalRows
     * @return List<AppointmentDto>
     */
    @GetMapping("/appointment/{patientId}/{pageNumber}/{totalRows}")
    public List<AppointmentDto> getAppointmentsByPatientId(@PathVariable(name = "patientId") int patientId,
                                                           @PathVariable(name = "pageNumber") int pageNumber,
                                                           @PathVariable(name = "totalRows") int totalRows) {
        return patientService.getAppointmentsByPatientId(patientId, pageNumber, totalRows);
    }
}

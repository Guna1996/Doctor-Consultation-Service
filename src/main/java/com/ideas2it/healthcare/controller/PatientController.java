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
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * PatientController class with the helps of getting inputs
 * from patient and passing to Patient Service to store the data's
 * into the database and it help to do CRUD operation
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
     * This method used to add patient details
     * by getting inputs from the patient
     * </p>
     *
     * @param patientDto is patient detail
     * @return String as ResponseEntity
     */
    @PostMapping
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto) {
        PatientDto addedPatient = patientService.addPatient(patientDto);
        return new ResponseEntity<>(addedPatient, HttpStatus.OK);
    }

    /**
     * <p>
     * This method used to get patient details
     * from the database by  getting patient id
     * from the user
     * </p>
     *
     * @param id is patient id
     * @return PatientDto as ResponseEntity
     */
    @GetMapping(Constants.ID)
    public ResponseEntity<PatientDto> getPatientById(@PathVariable int id) {
        PatientDto getPatient = patientService.getPatientById(id);
        return new ResponseEntity<>(getPatient, HttpStatus.OK);
    }

    /**
     * <p>
     * This method used to update patient details
     * from the database
     * </p>
     *
     * @param patientDto is detail of patient
     * @return PatientDto as ResponseEntity
     */
    @PutMapping
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto) {
        PatientDto updatePatient = patientService.updatePatient(patientDto);
        return new ResponseEntity<>(updatePatient, HttpStatus.OK);
    }

    /**
     * <p>
     * This method used to delete patient details
     * from the database by getting patient id
     * from the user
     * </p>
     *
     * @param id is detail of patient
     * @return String as ResponseEntity
     */
    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deletePatientById(@PathVariable int id) {
        String deletePatient = patientService.deletePatient(id);
        return new ResponseEntity<>(deletePatient, HttpStatus.OK);
    }

    /**
     * <p>
     * This method used to get all patient details
     * from the database
     * </p>
     *
     * @return List<PatientDto>
     */
    @GetMapping(Constants.PAGE_PATH)
    public ResponseEntity<List<PatientDto>> getPatients(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                        @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return new ResponseEntity<>(patientService.getPatients(pageNumber, totalRows), HttpStatus.OK);
    }
}

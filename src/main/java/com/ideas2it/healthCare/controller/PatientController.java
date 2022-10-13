/**
 * <p>
 * This is the package contains classes are DoctorClinicController,
 * PatientController,DoctorController,ClinicController,
 * AppointmentController,FeedbackController,SpecializationController,
 * TimeslotController,VitalsController
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.dto.PatientDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.service.Patientservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * PatientController class with the helps of getting inputs
 * from patient and passing to Patientservice to store the data's
 * into the database and it help to do CRUD operation
 * </p>
 *
 * @author Ramachandran
 *
 * @version 1
 *
 * @since 2022-07-18
 */
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private Patientservice patientservice;

    /**
     * <p>
     * This method used to add patient details
     * by getting inputs from the patient
     * </p>
     *
     * @Param patient - patient detail
     *
     * @return String
     */
    @PostMapping
    public ResponseEntity<PatientDto> addPatient(@Valid @RequestBody PatientDto patientDto) {
        PatientDto addedPatient = patientservice.addPatient(patientDto);
        return new ResponseEntity<>(addedPatient, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@RequestBody Integer id) throws NotFoundException {
        PatientDto getPatient = patientservice.getPatientById(id);
        return  new ResponseEntity<>(getPatient, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PatientDto> updatePatient(@Valid @RequestBody PatientDto patientDto) throws NotFoundException {
        PatientDto updatePatient = patientservice.updatePatient(patientDto);
        return new ResponseEntity<>(updatePatient, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> deletePatient(@RequestBody Integer id) throws NotFoundException {
        PatientDto deletePatient = patientservice.deletPatient(id);
        return new ResponseEntity<>(deletePatient, HttpStatus.OK);
    }
}

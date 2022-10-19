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
import com.ideas2it.healthCare.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


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
     * @Param id - patient id
     *
     * @return PatientDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Integer id)  {
        PatientDto getPatient = patientService.getPatientById(id);
        return  new ResponseEntity<>(getPatient, HttpStatus.OK);
    }

    /**
     * <p>
     * This method used to update patient details
     * from the database
     * </p>
     *
     * @Param patientDto
     *
     * @return PatientDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@Valid @RequestBody PatientDto patientDto) {
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
     * @Param id - patient id
     *
     * @return String
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Integer id) {
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
    @GetMapping
    public ResponseEntity<List<PatientDto>> getPatients() {
        return  new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }
}

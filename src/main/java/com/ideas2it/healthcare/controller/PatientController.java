/**
 * <p>
 * This is the package contains classes are Doctor clinic controller,
 * Patient controller, Doctor controller, Clinic controller,
 * Appointment controller, Feedback controller, Specialization controller,
 * Timeslot controller, Patient Vital controller
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.response.CustomResponse;
import com.ideas2it.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;

/**
 * <p>
 * This Patient Controller class is used to get and update
 * information from the patient and also used to get
 * their vitals and appointments.
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_PATIENT)
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private CustomResponse customResponse;

    /**
     * <p>
     * This method is used to add the details of a patient by getting details
     * such as name, date of birth, Gender, mobile number, etc from the patient
     * </p>
     *
     * @param patientDto {@link PatientDto} is patient detail
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addPatient(@Valid @RequestBody PatientDto patientDto) {
        return customResponse.responseEntity(patientService.addPatient(patientDto),
                null,
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get the details such as name, date of birth, Gender,
     * mobile number, etc of a patient by patient id
     * </p>
     *
     * @param id {@link Integer} is patient id
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> getPatientById(@PathVariable Integer id) {
        return customResponse.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_PATIENT,
                patientService.getPatientById(id),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update the details such as name, date of birth,
     * Gender, mobile number, etc of a patient
     * </p>
     *
     * @param patientDto {@link PatientDto} is detail of patient
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> updatePatient(@RequestBody PatientDto patientDto) {
        return customResponse.responseEntity(patientService.updatePatient(patientDto),
                null,
                HttpStatus.OK);
    }
}

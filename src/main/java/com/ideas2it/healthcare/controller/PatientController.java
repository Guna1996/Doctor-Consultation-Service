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
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.PatientDto;
import com.ideas2it.healthcare.response.UserResponse;
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
    private UserResponse userResponse;

    /**
     * <p>
     * This method is used to add the details of a patient by getting details
     * such as name, date of birth, Gender, mobile number, etc from the patient
     * and sent the Patient dto object into the Patient service layer to
     * add the patient details.
     * </p>
     *
     * @param patientDto {@link PatientDto} is patient detail
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, ?>> addPatient(@Valid @RequestBody PatientDto patientDto) {
        return userResponse.responseEntity(patientService.addPatient(patientDto),
                null,
                HttpStatus.CREATED);
    }

    /**
     * <p>
     * This method is used to get the particular patient which is
     * present in the database details such as name, date of birth,
     * Gender, mobile number, etc of a patient by patient id
     * </p>
     *
     * @param id {@link Integer} is patient id
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, ?>> getPatientById(@PathVariable Integer id) {
        PatientDto patientDto = patientService.getPatientById(id);
        String message = MessageConstants.SUCCESSFULLY_RETRIEVED_PATIENT;
        if (null == patientDto) {
            message = ErrorConstants.PATIENT_NOT_FOUND;
        }
        return userResponse.responseEntity(message, patientDto, HttpStatus.OK);

    }

    /**
     * <p>
     * This method is used to update the Patient details such as name,
     * date of birth, Gender, mobile number, etc of a patient
     *
     * </p>
     *
     * @param patientDto {@link PatientDto} is detail of patient
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String, ?>> updatePatient(@Valid @RequestBody PatientDto patientDto) {
        return userResponse.responseEntity(patientService.updatePatient(patientDto),
                null,
                HttpStatus.NO_CONTENT);
    }
}

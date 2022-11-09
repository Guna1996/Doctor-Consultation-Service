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
import com.ideas2it.healthcare.dto.PatientVitalDto;
import com.ideas2it.healthcare.response.UserResponse;
import com.ideas2it.healthcare.service.PatientVitalService;
import com.ideas2it.healthcare.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * This Patient Vital Controller class is used to manage
 * the vital information of a patient
 * doctor.
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_PATIENT_VITAL)
public class PatientVitalController {

    @Autowired
    private PatientVitalService patientVitalService;

    @Autowired
    private UserResponse userResponse;

    /**
     * <p>
     * This method is used to add vitals of a patient by getting details
     * such as height, weight, pulse, etc
     * </p>
     *
     * @param vitalsDto {@link PatientVitalDto} is a dto object that contains information
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, ?>> addVitals(@Valid @RequestBody PatientVitalDto vitalsDto) {
        return userResponse.responseEntity(patientVitalService.addVitals(vitalsDto),
                null,
                HttpStatus.CREATED);
    }

    /**
     * <p>
     * This method is used to get vitals of a patient along with pagination by
     * patient id, page number and total rows required. Based on the user input such
     * as page number and total rows total page is calculated and
     * displayed using pagination.
     * </p>
     *
     * @param patientId  {@link Integer} is id of patient
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_VITALS_BY_PATIENT_ID)
    public ResponseEntity<Map<String, ?>> getVitalByPatientId(
            @PathVariable(name = Constants.PATIENT_ID_PATH) Integer patientId,
            @RequestParam(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = patientVitalService.getVitalsCountByPatientId(patientId);
        int pages = MathUtil.pageCount(totalPages, totalRows);
        String message = MessageConstants.VITAL_RETRIEVED_SUCCESSFULLY;
        if (0 >= totalPages) {
            message = ErrorConstants.PATIENT_NOT_FOUND;
        } else if (pages <= pageNumber) {
            message = ErrorConstants.VITALS_NOT_FOUND;
        }
        return userResponse.responseEntity(message,
                patientVitalService.getVitalsByPatientId(patientId, pageNumber, totalRows),
                HttpStatus.PARTIAL_CONTENT, pages);
    }
}

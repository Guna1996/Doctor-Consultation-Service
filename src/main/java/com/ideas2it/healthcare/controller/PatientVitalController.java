/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.PatientVitalDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.response.CustomResponse;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * This VitalController class is used to manage
 * the vitals information of a patient from a ///
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
    private CustomResponse customResponse;

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
    public ResponseEntity<Map<String, Object>> addVitals(@Valid @RequestBody PatientVitalDto vitalsDto) {
        return customResponse.responseEntity(MessageConstants.VITALS_ADDED_SUCCESSFULLY,
                patientVitalService.addVitals(vitalsDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get vitals of a patient along with pagination by
     * patient id, page number and total rows required
     * </p>
     *
     * @param patientId  {@link Integer} is id of patient
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_VITALS_BY_PATIENT_ID + Constants.URL_PAGINATION)
    public ResponseEntity<Map<String, Object>> getVitalByPatientId(
            @PathVariable(name = Constants.PATIENT_ID_PATH) Integer patientId,
            @PathVariable(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(name = Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = patientVitalService.countOfVitalsByPatientId(patientId);
        int pages = MathUtil.pageCount(totalPages, totalRows);
        if (0 >= totalPages) {
            throw new NotFoundException(ErrorConstants.PATIENT_NOT_FOUND);
        }
        else if (pages <= pageNumber) {
            throw new NotFoundException(ErrorConstants.VITALS_NOT_FOUND);
        }
        return customResponse.responseEntity(MessageConstants.VITAL_RETRIEVED_SUCCESSFULLY,
                patientVitalService.getVitalsByPatientId(patientId, pageNumber, totalRows),
                HttpStatus.OK, pages);
    }
}

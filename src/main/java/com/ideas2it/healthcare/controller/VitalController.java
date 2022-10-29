/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.VitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * This VitalController class is used to manage
 * the vitals information of a patient from a
 * doctor.
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_VITAL)
public class VitalController {

    @Autowired
    private VitalService vitalService;

    /**
     * <p>
     * This method is used to add vitals
     * of a patient.
     *</p>
     *
     * @param vitalsDto {@link VitalDto} is a dto object that contains information
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addVitals(@RequestBody VitalDto vitalsDto) {
        return Response.responseEntity(Constants.VITALS_ADDED_SUCCESSFULLY,
                vitalService.addVitals(vitalsDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get
     * vitals of a patient.
     * </p>
     *
     * @param patientId {@link Integer}
     * @param pageNumber {@link Integer}
     * @param totalRows {@link Integer}
     * @return ResponseEntity<Map<String, Object>>
     */
    @GetMapping(Constants.VITAL_PATIENT_ID + Constants.PAGINATION)
    public ResponseEntity<Map<String, Object>> getVitalByPatientId(@PathVariable(name = Constants.PATH_PATIENT_ID) Integer patientId,
                                              @PathVariable(name = Constants.PAGE_NUMBER) Integer pageNumber,
                                              @PathVariable(name = Constants.TOTAL_ROWS) Integer totalRows) {
        return Response.responseEntity(Constants.VITAL_ADDED_SUCCESSFULLY,
                vitalService.getVitalsByPatientId(patientId, pageNumber, totalRows),
                HttpStatus.OK);
    }
}

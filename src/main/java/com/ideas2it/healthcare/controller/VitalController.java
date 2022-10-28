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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
     * This insertVitals method is used to add vitals
     * of a patient.
     *</p>
     *
     * @param vitalsDto is a dto object that contains information
     * @return VitalDto
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> insertVitals(@RequestBody VitalDto vitalsDto) {
        return Response.responseEntity("Success",
                vitalService.addVitals(vitalsDto),
                HttpStatus.OK);
    }
}

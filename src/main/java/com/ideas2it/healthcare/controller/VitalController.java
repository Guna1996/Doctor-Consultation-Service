/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.service.VitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * This VitalController class is a Controller class and this
 * class is used to get information and
 * transfer it to VitalDto
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(value = "/vital")
public class VitalController {

    @Autowired
    private VitalService vitalService;

    /**
     * <p>
     * Insert vitals details
     *</p>
     *
     * @param vitalsDto is a dto object that contains information
     * @return VitalDto
     */
    @PostMapping
    public VitalDto insertVitals(@RequestBody VitalDto vitalsDto) {
        return vitalService.addVitals(vitalsDto);
    }
}

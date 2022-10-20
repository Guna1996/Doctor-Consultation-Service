/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.ClinicDto;
import com.ideas2it.healthCare.service.ClinicService;
import lombok.RequiredArgsConstructor;
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

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * This ClinicController class is a Controller class and this
 * class is used to get input and display outputs for clinics
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@RequestMapping("/clinic")
@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    /**
     * add new clinics details
     *
     * @param clinicDto is clinic object
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<ClinicDto> addClinic(@Valid @RequestBody ClinicDto clinicDto) {

        return new ResponseEntity<>(clinicService.addClinic(clinicDto), HttpStatus.OK);
    }

    /**
     * Get all the Clinics
     *
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<ClinicDto>> getClinics() {

        return new ResponseEntity<>(clinicService.getClinics(), HttpStatus.OK);
    }

    /**
     * Get the Clinic by id
     *
     * @param id is clinic id
     * @return ResponseEntity
     */
    @GetMapping(Constants.ID)
    public ResponseEntity<ClinicDto> getClinicById(@PathVariable(Constants.PATH_ID) int id) {

        return new ResponseEntity<>(clinicService.getClinicById(id), HttpStatus.OK);
    }

    /**
     * Update clinic record by using it's id
     *
     * @param clinicDto is Loan object
     */
    @PutMapping
    public ResponseEntity<ClinicDto> updateClinic(@Valid @RequestBody ClinicDto clinicDto) {

        return new ResponseEntity<>(clinicService.updateClinic(clinicDto), HttpStatus.OK);
    }

    /**
     * Delete clinic by Id
     *
     * @param id is clinic id
     * @return string
     */
    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteClinic(@PathVariable("id") int id) {

        return new ResponseEntity<>(clinicService.deleteClinicById(id), HttpStatus.OK) ;
    }
}

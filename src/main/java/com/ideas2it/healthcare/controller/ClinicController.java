/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.service.ClinicService;
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
     * <p>
     * add new clinics details
     * </p>
     *
     * @param clinicDto is clinic object
     * @return ClinicDto as ResponseEntity
     */
    @PostMapping
    public ResponseEntity<ClinicDto> addClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return new ResponseEntity<>(clinicService.addClinic(clinicDto), HttpStatus.OK);
    }

    /**
     * <p>
     * Get all the Clinics
     * </p>
     *
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<ClinicDto> as ResponseEntity
     */
    @GetMapping(Constants.PAGE_PATH)
    public ResponseEntity<List<ClinicDto>> getClinics(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                      @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return new ResponseEntity<>(clinicService.getClinics(pageNumber, totalRows), HttpStatus.OK);
    }

    /**
     * <p>
     * Get the Clinic by id
     * </p>
     *
     * @param id is clinic id
     * @return ClinicDto as ResponseEntity
     */
    @GetMapping(Constants.ID)
    public ResponseEntity<ClinicDto> getClinicById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(clinicService.getClinicById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * Update clinic record by using it's id
     * </p>
     *
     * @param clinicDto is Loan object
     * @return ClinicDto as ResponseEntity
     */
    @PutMapping
    public ResponseEntity<ClinicDto> updateClinic(@Valid @RequestBody ClinicDto clinicDto) {
        return new ResponseEntity<>(clinicService.updateClinic(clinicDto), HttpStatus.OK);
    }

    /**
     * <p>
     * Delete clinic by Id
     * </p>
     *
     * @param id is clinic id
     * @return String as ResponseEntity
     */
    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteClinic(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(clinicService.deleteClinicById(id), HttpStatus.OK);
    }
}

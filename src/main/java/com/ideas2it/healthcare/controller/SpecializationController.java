/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.SpecializationDto;
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.SpecializationService;
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
import java.util.List;
import java.util.Map;

/**
 * <p>
 * This SpecializationController class is used to
 * assign specializations to doctors.
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_SPECIALIZATION)
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    /**
     * <p>
     * This method is used to add details of Specialization
     * after validating it.
     * </p>
     *
     * @param specializationDto is details of specialization
     * @return specializationDto
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addSpecialization(@Valid @RequestBody SpecializationDto specializationDto) {
        return Response.responseEntity("Success",
                specializationService.saveSpecialization(specializationDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get All the details
     * of Specialization.
     * </p>
     *
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<SpecializationDto>
     */
    @GetMapping(Constants.PAGINATION)
    public ResponseEntity<Map<String, Object>> getAllSpecializations(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                         @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return Response.responseEntity("Success",
                specializationService.getAllSpecializations(pageNumber, totalRows),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get details of a particular specialization
     * by particular specialization id.
     * </p>
     *
     * @param id is id of Specialization
     * @return SpecializationDto
     */
    @GetMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> getSpecializationById(@PathVariable int id) {
        return Response.responseEntity("Sucess",
                specializationService.getSpecializationById(id),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update the details of
     * Specialization after validating it.
     * </p>
     *
     * @param specializationDto is details of SpecializationDto
     * @return SpecializationDto
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateSpecialization(@RequestBody SpecializationDto specializationDto) {
        return Response.responseEntity("Success",
                specializationService.updateSpecialization(specializationDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove the Specialization
     *  by specialization id
     * </p>
     *
     * @param id is id of Specialization
     * @return String
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<String> deleteSpecializationById(@PathVariable int id) {
        return new ResponseEntity<>(specializationService.deleteSpecializationById(id), HttpStatus.OK);
    }
}
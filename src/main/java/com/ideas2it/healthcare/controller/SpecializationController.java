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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     * This method is used to add details
     * of a Specialization.
     * </p>
     *
     * @param specializationDto {@link SpecializationDto}is details of specialization
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addSpecialization(@Valid @RequestBody SpecializationDto specializationDto) {
        return Response.responseEntity(Constants.SPECIALIZATION_ADDED_SUCCESSFULLY,
                specializationService.saveSpecialization(specializationDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get all the details
     * of Specialization.
     * </p>
     *
     * @param pageNumber {@link Integer} is page number to show
     * @param totalRows  {@link Integer} is a set of rows to be shown
     * @return ResponseEntity<Map<String, Object>>
     */
    @GetMapping(Constants.PAGINATION)
    public ResponseEntity<Map<String, Object>> getAllSpecializations(@PathVariable(Constants.PAGE_NUMBER) Integer pageNumber,
                                                                     @PathVariable(Constants.TOTAL_ROWS) Integer totalRows) {
        return Response.responseEntity(Constants.SUCCESSFULLY_RETRIEVED_SPECIALIZATIONS,
                specializationService.getAllSpecializations(pageNumber, totalRows),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get details of a
     * particular specialization.
     * </p>
     *
     * @param id {@link Integer} is id of Specialization
     * @return ResponseEntity<Map<String, Object>>
     */
    @GetMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> getSpecializationById(@PathVariable Integer id) {
        return Response.responseEntity(Constants.SUCCESSFULLY_RETRIEVED_SPECIALIZATION,
                specializationService.getSpecializationById(id),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update the details
     * of a Specialization.
     * </p>
     *
     * @param specializationDto {@link SpecializationDto} is details of SpecializationDto
     * @return ResponseEntity<Map<String, Object>>
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateSpecialization(@RequestBody SpecializationDto specializationDto) {
        return Response.responseEntity(Constants.SPECIALIZATION_UPDATED_SUCCESSFULLY,
                specializationService.updateSpecialization(specializationDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove the
     * details of a Specialization.
     * </p>
     *
     * @param id {@link Integer} is id of Specialization
     * @return ResponseEntity<String>
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<String> deleteSpecializationById(@PathVariable Integer id) {
        return new ResponseEntity<>(specializationService.deleteSpecializationById(id), HttpStatus.OK);
    }
}
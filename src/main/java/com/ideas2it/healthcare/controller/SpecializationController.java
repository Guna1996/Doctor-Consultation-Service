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
import com.ideas2it.healthcare.service.SpecializationService;
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
 * This SpecializationController class is a Controller class and this
 * class is used to get information from the specialization and
 * transfer it to SpecializationDto
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/specialization")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    /**
     * <p>
     * This method is used to get details of Specialization
     * after validating it
     * </p>
     *
     * @param specializationDto is details of specialization
     * @return specializationDto as ResponseEntity
     */
    @PostMapping
    public ResponseEntity<SpecializationDto> addSpecialization(@Valid @RequestBody SpecializationDto specializationDto) {
        return new ResponseEntity<>(specializationService.saveOrUpdateSpecialization(specializationDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get All the details of
     * Specialization form the service through Specialization Dto
     * </p>
     *
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<SpecializationDto> as ResponseEntity
     */
    @GetMapping(Constants.PAGE_PATH)
    public ResponseEntity<List<SpecializationDto>> getAllSpecializations(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                                         @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return new ResponseEntity<>(specializationService.getAllSpecializations(pageNumber, totalRows), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get details of Specialization
     * of a particular doctor by id
     * </p>
     *
     * @param id is id of Specialization
     * @return SpecializationDto as ResponseEntity
     */
    @GetMapping(Constants.ID)
    public ResponseEntity<SpecializationDto> getSpecializationById(@PathVariable int id) {
        return new ResponseEntity<>(specializationService.getSpecializationById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update the details of
     * Specialization after validate
     * </p>
     *
     * @param specializationDto is details of SpecializationDto
     * @return SpecializationDto as ResponseEntity
     */
    @PutMapping
    public ResponseEntity<SpecializationDto> updateSpecialization(@RequestBody SpecializationDto specializationDto) {
        return new ResponseEntity<>(specializationService.saveOrUpdateSpecialization(specializationDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to delete the details of
     * Specialization by getting the id
     * </p>
     *
     * @param id is id of Specialization
     * @return String as ResponseEntity
     */
    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteSpecializationById(@PathVariable int id) {
        return new ResponseEntity<>(specializationService.deleteSpecializationById(id), HttpStatus.OK);
    }
}
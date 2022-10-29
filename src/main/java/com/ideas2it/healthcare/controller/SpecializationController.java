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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("/specialization")
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
    public SpecializationDto addSpecialization(@RequestBody SpecializationDto specializationDto) {
        return specializationService.saveOrUpdateSpecialization(specializationDto);
    }

    /**
     * <p>
     * This method is used to get
     * all the details of Specialization.
     * </p>
     *
     * @param pageNumber is page number to show
     * @param totalRows  is a set of rows to be shown
     * @return List<SpecializationDto>
     */
    @GetMapping(Constants.PAGE_PATH)
    public List<SpecializationDto> getAllSpecializations(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                         @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return specializationService.getAllSpecializations(pageNumber, totalRows);
    }

    /**
     * <p>
     * This method is used to get details of Specialization
     * of a particular Specialization by id.
     * </p>
     *
     * @param id is id of Specialization
     * @return SpecializationDto
     */
    @GetMapping(Constants.PATH_ID)
    public SpecializationDto getSpecializationById(@PathVariable int id) {
        return specializationService.getSpecializationById(id);
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
    public SpecializationDto updateSpecialization(@RequestBody SpecializationDto specializationDto) {
        return specializationService.saveOrUpdateSpecialization(specializationDto);
    }

    /**
     * <p>
     * This deleteSpecializationById method is used to
     * remove the details of Specialization.
     * </p>
     *
     * @param id is id of Specialization
     * @return String
     */
    @PutMapping(Constants.PATH_ID)
    public String deleteSpecializationById(@PathVariable int id) {
        return specializationService.deleteSpecializationById(id);
    }
}
/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.dto.SpecializationDto;
import com.ideas2it.healthcare.service.SpecializationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * This SpecializationController class is a Controller class and this
 * class is used to get information from the clint and
 * transfer it to SpecializationDto
 * </p>
 *
 * @author  Mohamed Jubair
 *
 * @version 1
 *
 * @since   2022-10-10
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
     * @param specializationDto {@link SpecializationDto}
     *
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<SpecializationDto> addSpecialization(@Valid @RequestBody SpecializationDto specializationDto){
        return new ResponseEntity<>(specializationService.saveOrUpdateSpecialization(specializationDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get All the details of
     * specialization form the service through Specialization Dto
     * </p>
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping("/{pageNumber}/{totalRows}")
    public ResponseEntity<List<SpecializationDto>>  getAllSpecializations(@PathVariable("pageNumber") int pageNumber
            , @PathVariable("totalRows") int totalRows) {
        return new ResponseEntity<>(specializationService.getAllSpecializations(pageNumber, totalRows),HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get details of specialization
     * of a particular doctor by id
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping("/{id}")
    public ResponseEntity<SpecializationDto> getSpecializationById(@PathVariable int id) {
        return new ResponseEntity<>(specializationService.getSpecializationById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update the details of
     * specialization after validate
     * </p>
     *
     * @param specializationDto {@link SpecializationDto}
     *
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<SpecializationDto> updateSpecialization(@RequestBody SpecializationDto specializationDto) {
        return new ResponseEntity<>(specializationService.saveOrUpdateSpecialization(specializationDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to delete the details of
     * specialization by getting their id
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link ResponseEntity}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpecializationById(@PathVariable int id) {
        return new ResponseEntity<>(specializationService.deleteSpecializationById(id), HttpStatus.OK);
    }
}

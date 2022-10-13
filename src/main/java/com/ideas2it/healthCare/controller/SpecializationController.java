/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.dto.SpecializationDto;
import com.ideas2it.healthCare.service.SpecializationService;

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
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @PostMapping(value = "/addSpecialization")
    public ResponseEntity<SpecializationDto> addSpecialization(@Valid @RequestBody SpecializationDto specializationDto){
        return new ResponseEntity<>(specializationService.saveOrUpdate(specializationDto), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllSpecializations")
    public ResponseEntity<List<SpecializationDto>>  getAllSpecializations() {
        return new ResponseEntity<>(specializationService.getAllSpecializations(),HttpStatus.OK);
    }

    @GetMapping(value = "/getSpecializationById/{id}")
    public ResponseEntity<SpecializationDto> getSpecializationById(@PathVariable int id) {
        return new ResponseEntity<>(specializationService.getSpecializationById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/updateSpecialization/")
    public ResponseEntity<SpecializationDto> updateSpecialization(@RequestBody SpecializationDto specializationDto) {
        return new ResponseEntity<>(specializationService.saveOrUpdate(specializationDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteSpecializationById/{id}")
    public ResponseEntity<String> deleteSpecializationById(@PathVariable int id) {
        return new ResponseEntity<>(specializationService.deleteById(id), HttpStatus.OK);
    }
}

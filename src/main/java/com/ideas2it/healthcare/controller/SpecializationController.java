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

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/specialization")
public class SpecializationController {

    private final SpecializationService specializationService;

    @PostMapping
    public ResponseEntity<SpecializationDto> addSpecialization(@Valid @RequestBody SpecializationDto specializationDto){
        specializationDto.setStatus(Constants.ACTIVE);
        return new ResponseEntity<>(specializationService.saveOrUpdateSpecialization(specializationDto), HttpStatus.OK);
    }

    @GetMapping("/{pageNumber}/{totalRows}")
    public ResponseEntity<List<SpecializationDto>>  getAllSpecializations(@PathVariable("pageNumber") int pageNumber
            , @PathVariable("totalRows") int totalRows) {
        return new ResponseEntity<>(specializationService.getAllSpecializations(pageNumber, totalRows),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SpecializationDto> getSpecializationById(@PathVariable int id) {
        return new ResponseEntity<>(specializationService.getSpecializationById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SpecializationDto> updateSpecialization(@RequestBody SpecializationDto specializationDto) {
        return new ResponseEntity<>(specializationService.saveOrUpdateSpecialization(specializationDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpecializationById(@PathVariable int id) {
        return new ResponseEntity<>(specializationService.deleteSpecializationById(id), HttpStatus.OK);
    }
}
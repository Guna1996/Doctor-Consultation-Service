/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.DoctorSpecializationDto;
import com.ideas2it.healthCare.dto.SpecializationDto;
import com.ideas2it.healthCare.service.SpecializationService;

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
    public ResponseEntity<DoctorSpecializationDto> addSpecialization(@Valid @RequestBody DoctorSpecializationDto specializationDto){
        specializationDto.setStatus(Constants.ACTIVE);
        return new ResponseEntity<>(specializationService.saveOrUpdate(specializationDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DoctorSpecializationDto>>  getAllSpecializations() {
        return new ResponseEntity<>(specializationService.getAllSpecializations(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorSpecializationDto> getSpecializationById(@PathVariable int id) {
        return new ResponseEntity<>(specializationService.getSpecializationById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<DoctorSpecializationDto> updateSpecialization(@RequestBody DoctorSpecializationDto specializationDto) {
        return new ResponseEntity<>(specializationService.saveOrUpdate(specializationDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpecializationById(@PathVariable int id) {
        DoctorSpecializationDto specializationDto = specializationService.getSpecializationById(id);
        specializationDto.setStatus(Constants.INACTIVE);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}

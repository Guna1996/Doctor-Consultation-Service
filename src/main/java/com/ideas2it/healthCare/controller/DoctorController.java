/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * This DoctorController class is a Controller class and this
 * class is used to get information from the clint and
 * transfer it to doctor Dto
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
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDto> addDoctor(@Valid @RequestBody DoctorDto doctorDto){
        doctorDto.setStatus(Constants.ACTIVE);
        return new ResponseEntity<>(doctorService.saveOrUpdate(doctorDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>>  getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable int id) {
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<DoctorDto> updateDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return new ResponseEntity<>(doctorService.saveOrUpdate(doctorDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable int id) {
        DoctorDto doctorDto = doctorService.getDoctorById(id);
        doctorDto.setStatus(Constants.INACTIVE);
        doctorService.saveOrUpdate(doctorDto);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}

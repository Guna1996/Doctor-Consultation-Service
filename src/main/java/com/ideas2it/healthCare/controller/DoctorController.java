/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping(value = "/addDoctor")
    public ResponseEntity<DoctorDto> addDoctor(@Valid @RequestBody DoctorDto doctorDto){
        return new ResponseEntity<>(doctorService.saveOrUpdate(doctorDto), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllDoctors")
    public ResponseEntity<List<DoctorDto>>  getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(),HttpStatus.OK);
    }

    @GetMapping(value = "/getDoctorById/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable int id) {
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/updateDoctor/")
    public ResponseEntity<DoctorDto> updateDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return new ResponseEntity<>(doctorService.saveOrUpdate(doctorDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteDoctorById/{id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable int id) {
        return new ResponseEntity<>(doctorService.deleteById(id), HttpStatus.OK);
    }
}

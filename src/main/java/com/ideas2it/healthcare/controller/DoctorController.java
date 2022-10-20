/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.service.DoctorService;
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
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private  DoctorService doctorService;

    /**
     * <p>
     * This method is used to get details of doctor
     * after validating it
     * </p>
     *
     * @param doctorDto {@link DoctorDto}
     *
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<DoctorDto> addDoctor(@Valid @RequestBody DoctorDto doctorDto){
        return new ResponseEntity<>(doctorService.saveOrUpdateDoctor(doctorDto), HttpStatus.OK);
    }


    /**
     * <p>
     * This method is used to get All the details of
     * doctor form the service through Doctor Dto
     * </p>
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping("/{pageNumber}/{totalRows}")
    public ResponseEntity<List<DoctorDto>> getAllDoctors(@PathVariable("pageNumber") int pageNumber
            , @PathVariable("totalRows") int totalRows) {
        return new ResponseEntity<>(doctorService.getAllDoctors(pageNumber, totalRows),HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get details of doctor
     * of a particular doctor by id
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable int id) {
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update the details of
     * doctor after validate
     * </p>
     *
     * @param doctorDto {@link DoctorDto}
     *
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<DoctorDto> updateDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return new ResponseEntity<>(doctorService.saveOrUpdateDoctor(doctorDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to delete the details of
     * doctor by getting their id
     * </p>
     *
     * @param id {@link int}
     *
     * @return {@link ResponseEntity}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable int id) {
        return new ResponseEntity<>(doctorService.deleteDoctorById(id), HttpStatus.OK);
    }
}

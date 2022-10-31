/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.DoctorDto;
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * This DoctorController class is used to get and store information from a
 * doctor, update and delete doctors and get appointment, feedback of a
 * doctor
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */

@RestController
@RequestMapping(Constants.URL_DOCTOR)
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    /**
     * <p>
     * This method is used to add details of
     * a doctor.
     * </p>
     *
     * @param doctorDto {@link DoctorDto} is details of doctor
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String,Object>> addDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return Response.responseEntity(MessageConstants.DOCTOR_ADDED_SUCCESSFULLY,
                doctorService.saveDoctor(doctorDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get All the
     * details of doctors.
     * </p>
     *
     * @param pageNumber {@link Integer} is page number
     * @param totalRows {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_PAGINATION)
    public ResponseEntity<Map<String, Object>> getAllDoctors(
            @PathVariable(Constants.PAGE_NUMBER) int pageNumber,
            @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return Response.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_DOCTORS,
                doctorService.getAllDoctors(pageNumber, totalRows),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get details
     * of a particular doctor.
     * </p>
     *
     * @param id {@link Integer} is id of doctor
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> getDoctorById(@PathVariable int id) {
        return Response.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_DOCTOR,
                doctorService.getDoctorById(id),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update the
     * details of doctor.
     * </p>
     *
     * @param doctorDto {@link DoctorDto} is details of doctor
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String,Object>> updateDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        return Response.responseEntity(MessageConstants.DOCTOR_UPDATED_SUCCESSFULLY,
                doctorService.updateDoctor(doctorDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove
     * the details of a doctor.
     * </p>
     *
     * @param id {@link Integer} is id of doctor
     * @return {@link ResponseEntity}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<String> deleteDoctorById(@PathVariable int id) {
        return new ResponseEntity<>(doctorService.deleteDoctorById(id), HttpStatus.OK);
    }
}
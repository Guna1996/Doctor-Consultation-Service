/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.service.VitalService;
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

import java.util.List;

/**
 * <p>
 * This VitalController class is a Controller class and this
 * class is used to get information and
 * transfer it to VitalDto
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(value = "/vital")
public class VitalController {

    @Autowired
    private VitalService vitalService;

    /**
     * <p>
     * Gets all Vital details
     * </p>
     *
     * @param pageNumber - page number to show
     * @param totalRows  - a set of rows to be shown
     * @return VitalDto as ResponseEntity
     */
    @GetMapping(Constants.PAGE_PATH)
    public ResponseEntity<List<VitalDto>> getAllVitals(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                       @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return new ResponseEntity<>(vitalService.getVitals(pageNumber, totalRows), HttpStatus.OK);
    }

    /**
     * <p>
     * Update vital details
     * </p>
     *
     * @param vitalsDto is a dto that contains information to update
     * @return VitalDto as ResponseEntity
     */
    @PutMapping
    public ResponseEntity<VitalDto> updateVitals(@RequestBody VitalDto vitalsDto) {
        return new ResponseEntity<>(vitalService.updateVitals(vitalsDto), HttpStatus.OK);
    }

    /**
     * <p>
     * Get Vital details
     *</p>
     *
     * @param id is an integer that refers id on the database
     * @return VitalDto as ResponseEntity
     */
    @GetMapping(Constants.ID)
    public ResponseEntity<VitalDto> getVitalById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(vitalService.getVitalsById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * Insert vitals details
     *</p>
     *
     * @param vitalsDto is a dto object that contains information
     * @return VitalDto as ResponseEntity
     */
    @PostMapping
    public ResponseEntity<VitalDto> insertFeedback(@RequestBody VitalDto vitalsDto) {
        vitalsDto.setStatus(Constants.ACTIVE);
        return new ResponseEntity<>(vitalService.addVitals(vitalsDto), HttpStatus.OK);
    }

    /**
     * <p>
     * delete Vital details
     * </p>
     *
     * @param id is an integer that refer id in database
     * @return String as ResponseEntity
     */
    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteFeedbackById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(vitalService.deleteVitals(id), HttpStatus.OK);
    }

    /**
     * get Timeslot details
     *
     * @param patientId  - integer that refer patient_id in database
     * @param pageNumber - page number to show
     * @param totalRows  - a set of rows to be shown
     * @return List<VitalDto> as ResponseEntity
     */
    @GetMapping(Constants.GET_PATIENT_PATH)
    public ResponseEntity<List<VitalDto>> getVitalsByPatientId(@PathVariable(Constants.PATIENT_ID) int patientId,
                                                               @PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                               @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return new ResponseEntity<>(vitalService
                .getVitalsByPatientId(patientId, pageNumber, totalRows), HttpStatus.OK);
    }
}

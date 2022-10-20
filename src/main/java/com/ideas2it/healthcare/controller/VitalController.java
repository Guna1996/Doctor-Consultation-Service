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
import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/vital")
public class VitalController {

    private final VitalService vitalService;

    /**
     * gets all Vital details
     *
     * @param pageNumber - page number to show
     * @param totalRows - a set of rows to be shown
     * @return ResponseEntity
     */
    @GetMapping("/{pageNumber}/{totalRows}")
    public ResponseEntity<List<VitalDto>> getAllVitals(@PathVariable("pageNumber") int pageNumber,
                                                       @PathVariable("totalRows") int totalRows) {
        return new ResponseEntity<>(vitalService.getVitals(pageNumber, totalRows), HttpStatus.OK);
    }

    /**
     * update vital details
     *
     * @param vitalsDto - a dto that contains information to update
     * @return ResponseEntity
     */
    @PutMapping
    public ResponseEntity<VitalDto> updateVitals(@RequestBody VitalDto vitalsDto) {
        return new ResponseEntity<>(vitalService.updateVitals(vitalsDto),HttpStatus.OK);
    }

    /**
     * get Vital details
     *
     * @param id - an integer that refers id on the database
     * @return ResponseEntity
     */
    @GetMapping(Constants.ID)
    public ResponseEntity<VitalDto> getVitalById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(vitalService.getVitalsById(id), HttpStatus.OK);
    }

    /**
     * insert vitals details
     *
     * @param vitalsDto - a dto object that contains information
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<VitalDto> insertFeedback(@RequestBody VitalDto vitalsDto) {
        vitalsDto.setStatus(Constants.ACTIVE);
        return new ResponseEntity<>(vitalService.addVitals(vitalsDto),HttpStatus.OK);
    }

    /**
     * delete Vital details
     *
     * @param id - a integer that refer id in database
     * @return ResponseEntity
     */
    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteFeedbackById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(vitalService.deleteVitals(id), HttpStatus.OK);
    }

    /**
     * get Timeslot details
     *
     * @param patientId - integer that refer patient_id in database
     * @param pageNumber - page number to show
     * @param totalRows - a set of rows to be shown
     * @return ResponseEntity
     */
    @GetMapping("/patient/{patientId}/{pageNumber}/{totalRows}")
    public ResponseEntity<List<VitalDto>> getVitalsByPatientId(@PathVariable("patientId") int patientId,
                                                               @PathVariable("pageNumber") int pageNumber,
                                                               @PathVariable("totalRows") int totalRows) {
        return new ResponseEntity<>(vitalService
                .getVitalsByPatientId(patientId, pageNumber, totalRows), HttpStatus.OK);
    }
}

/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.service.TimeslotService;
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
 * This TimeslotController class is a Controller class and this
 * class is used to get information and
 * transfer it to TimeslotDto
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/timeslot")
public class TimeslotController {

    @Autowired
    private TimeslotService timeslotService;

    /**
     * <p>
     * Gets all timeslot details
     * </p>
     *
     * @param pageNumber - page number to show
     * @param totalRows  - a set of rows to be shown
     * @return TimeslotDto as ResponseEntity
     */
    @GetMapping(Constants.PAGE_PATH)
    public ResponseEntity<List<TimeslotDto>> getAllTimeslots(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                             @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return new ResponseEntity<>(timeslotService.getTimeslots(pageNumber, totalRows), HttpStatus.OK);
    }

    /**
     * <p>
     * Update timeslot details
     * </p>
     *
     * @param timeslotDto is a dto that contains information to update
     * @return TimeslotDto as ResponseEntity
     */
    @PutMapping
    public ResponseEntity<TimeslotDto> updateTimeslot(@RequestBody TimeslotDto timeslotDto) {
        return new ResponseEntity<>(timeslotService.updateTimeslot(timeslotDto), HttpStatus.OK);
    }

    /**
     * <p>
     * Get Timeslot details
     * </p>
     *
     * @param id is an integer that refers id on the database
     * @return TimeslotDto as ResponseEntity
     */
    @GetMapping(Constants.ID)
    public ResponseEntity<TimeslotDto> getTimeslotById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(timeslotService.getTimeslotById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * Insert Timeslot details
     * </p>
     *
     * @param timeslotDto is a dto object that contains information
     * @return TimeslotDto as ResponseEntity
     */
    @PostMapping
    public ResponseEntity<TimeslotDto> insertFeedback(@RequestBody TimeslotDto timeslotDto) {
        return new ResponseEntity<>(timeslotService.addTimeslot(timeslotDto), HttpStatus.OK);
    }

    /**
     * <p>
     * Delete Timeslot details
     * </p>
     *
     * @param id is an integer that refer id in database
     * @return String as ResponseEntity
     */
    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteTimeslotById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(timeslotService.deleteTimeslot(id), HttpStatus.OK);
    }
}

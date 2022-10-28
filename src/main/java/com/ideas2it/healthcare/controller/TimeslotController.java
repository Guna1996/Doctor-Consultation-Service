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
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * This TimeslotController class is used to manage
 * all the timeslots of clinics and doctors.
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_TIMESLOT)
public class TimeslotController {

    @Autowired
    private TimeslotService timeslotService;

    /**
     * <p>
     * This getAllTimeslots method is used to get
     * timeslots.
     * </p>
     *
     * @param pageNumber - page number to show
     * @param totalRows  - a set of rows to be shown
     * @return List<TimeslotDto>
     */
    @GetMapping(Constants.PAGINATION)
    public ResponseEntity<Map<String, Object>> getAllTimeslots(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                               @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return Response.responseEntity("Success",
                timeslotService.getTimeslots(pageNumber, totalRows),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This insertTimeslot method is used to add
     * timeslot.
     * </p>
     *
     * @param timeslotDto is a dto object that contains information
     * @return TimeslotDto
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> insertTimeslot(@RequestBody TimeslotDto timeslotDto) {
        return Response.responseEntity("Success",
                timeslotService.addTimeslot(timeslotDto),
                HttpStatus.OK);
    }
}

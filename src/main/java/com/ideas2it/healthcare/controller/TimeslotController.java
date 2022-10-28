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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<TimeslotDto> getAllTimeslots(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                             @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return timeslotService.getTimeslots(pageNumber, totalRows);
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
    public TimeslotDto insertTimeslot(@RequestBody TimeslotDto timeslotDto) {
        return timeslotService.addTimeslot(timeslotDto);
    }
}

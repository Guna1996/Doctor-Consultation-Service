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
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.response.SuccessResponse;
import com.ideas2it.healthcare.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * This method is used to get
     * timeslots.
     * </p>
     *
     * @param pageNumber {@link Integer} is page number
     * @param totalRows {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_PAGINATION)
    public ResponseEntity<Map<String, Object>> getAllTimeslots(
            @PathVariable(Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(Constants.TOTAL_ROWS) Integer totalRows) {
        return SuccessResponse.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_TIMESLOTS,
                timeslotService.getTimeslots(pageNumber, totalRows),
                HttpStatus.OK, timeslotService.getTotalPages());
    }

    /**
     * <p>
     * This method is used to add timeslot.
     * </p>
     *
     * @param timeslotDto {@link TimeslotDto} is a dto object that contains information
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addTimeslot(@RequestBody TimeslotDto timeslotDto) {
        return SuccessResponse.responseEntity(MessageConstants.TIMESLOT_ADDED_SUCCESSFULLY,
                timeslotService.addTimeslot(timeslotDto),
                HttpStatus.OK);
    }
}

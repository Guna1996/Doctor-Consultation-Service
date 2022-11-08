/**
 * <p>
 * This is the package contains classes are Doctor clinic controller,
 * Patient controller, Doctor controller, Clinic controller,
 * Appointment controller, Feedback controller, Specialization controller,
 * Timeslot controller, Patient Vital controller
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.exception.CustomException;
import com.ideas2it.healthcare.response.CustomResponse;
import com.ideas2it.healthcare.service.TimeslotService;
import com.ideas2it.healthcare.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * This Timeslot Controller class is used to manage
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

    @Autowired
    private CustomResponse customResponse;

    /**
     * <p>
     * This method is used to add timeslot by getting details
     * such as timeslot name and status from the admin
     * </p>
     *
     * @param timeslotDto {@link TimeslotDto} is a dto object that contains information
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addTimeslot(@Valid @RequestBody TimeslotDto timeslotDto) {
        return customResponse.responseEntity(timeslotService.addTimeslot(timeslotDto),
                null,
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get timeslots along with pagination
     * by getting page number and total rows required. Based on
     * the user input such as page number and total rows total
     * page is calculated and displayed using pagination.
     * </p>
     *
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_PAGINATION)
    public ResponseEntity<Map<String, Object>> getAllTimeslots(
            @PathVariable(Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = timeslotService.getTimeslotsCount();
        int pages = MathUtil.pageCount(totalPages, totalRows);
        String message = MessageConstants.SUCCESSFULLY_RETRIEVED_TIMESLOTS;
        if (pages <= pageNumber) {
            message = ErrorConstants.TIMESLOTS_NOT_FOUND;
        }
        return customResponse.responseEntity(message,
                timeslotService.getTimeslots(pageNumber, totalRows),
                HttpStatus.OK, pages);
    }
}

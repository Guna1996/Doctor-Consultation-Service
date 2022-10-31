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
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.response.SuccessResponse;
import com.ideas2it.healthcare.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * This FeedbackController class is used to get feedback
 * from the users.
 * </p>
 *
 * @author Bala Ashwanth
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_FEEDBACK)
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * <p>
     * This method is used to add a feedback
     * from a patient.
     * </p>
     *
     * @param feedbackDto {@link FeedbackDto} is contains details of feedback
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addFeedback(@RequestBody FeedbackDto feedbackDto) {
        return SuccessResponse.responseEntity(MessageConstants.FEEDBACK_ADDED_SUCCESSFULLY,
                feedbackService.addFeedback(feedbackDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove feedback of
     * a patient.
     * </p>
     *
     * @param id {@link Integer} is an integer that refer id in database
     * @return {@link ResponseEntity}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<String> deleteFeedbackById(@PathVariable(Constants.ID) Integer id) {
        return new ResponseEntity<>(feedbackService.deleteFeedback(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get feedbacks
     * of a doctor.
     * </p>
     *
     * @param doctorId {@link Integer} is id of doctor
     * @param pageNumber {@link Integer} is page number
     * @param totalRows {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_FEEDBACKS_BY_DOCTOR_ID)
    public ResponseEntity<Map<String, Object>> getFeedbacksByDoctorId(
            @PathVariable(name = Constants.DOCTOR_ID) Integer doctorId,
            @PathVariable(name = Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(name = Constants.TOTAL_ROWS) Integer totalRows) {
        return SuccessResponse.responseEntity(MessageConstants.SUCCESSFULLY_RETRIEVED_FEEDBACK_TO_DOCTOR,
                feedbackService.getFeedbackByDoctorId(doctorId, pageNumber, totalRows),
                HttpStatus.OK);
    }
}

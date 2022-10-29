/**
 * <p>
 * This is the base package for all the controller classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.response.Response;
import com.ideas2it.healthcare.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
     * This method is used to add a feedback from a patient
     * into the database
     * </p>
     *
     * @param feedbackDto is dto object that contains information
     * @return FeedbackDto
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addFeedback(@RequestBody FeedbackDto feedbackDto) {
        return Response.responseEntity("Succes",
                feedbackService.addFeedback(feedbackDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove feedback from the database
     * by feedback id
     * </p>
     *
     * @param id is an integer that refer id in database
     * @return String
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<String> deleteFeedbackById(@PathVariable(Constants.ID) int id) {
        return new ResponseEntity<>(feedbackService.deleteFeedback(id), HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to get feedbacks
     * of a doctor.
     * </p>
     *
     * @param doctorId
     * @param pageNumber
     * @param totalRows
     * @return List<FeedbackDto>
     */
    @GetMapping(Constants.PATH_FEEDBACK_ID)
    public List<FeedbackDto> getFeedbacksByDoctorId(@PathVariable(name = Constants.PATH_DOCTOR_ID) int doctorId,
                                                    @PathVariable(name = Constants.PAGE_NUMBER) int pageNumber,
                                                    @PathVariable(name = Constants.TOTAL_ROWS) int totalRows) {
        return feedbackService.getFeedbackByDoctorId(doctorId, pageNumber, totalRows);
    }
}

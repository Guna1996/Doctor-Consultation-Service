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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * <p>
     * This insertFeedback method is used to get
     * a feedback from a patient.
     * </p>
     *
     * @param feedbackDto is dto object that contains information
     * @return FeedbackDto
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> insertFeedback(@RequestBody FeedbackDto feedbackDto) {
        return Response.responseEntity("Succes",
                feedbackService.addFeedback(feedbackDto),
                HttpStatus.OK);
    }

    /**
     * <p>
     * This deleteFeedbackById method is used to remove
     * unwanted feedback from the clinic.
     * </p>
     *
     * @param id is an integer that refer id in database
     * @return String
     */
    @PutMapping(Constants.PATH_ID)
    public ResponseEntity<String> deleteFeedbackById(@PathVariable(Constants.ID) int id) {
        return new ResponseEntity<>(feedbackService.deleteFeedback(id), HttpStatus.OK);
    }
}

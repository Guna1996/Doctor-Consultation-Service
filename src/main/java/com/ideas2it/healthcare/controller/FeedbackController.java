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
import com.ideas2it.healthcare.service.FeedbackService;
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

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * <p>
     * gets all Feedback details
     * </p>
     *
     * @param pageNumber - page number to show
     * @param totalRows  - a set of rows to be shown
     * @return List<FeedbackDto> as ResponseEntity
     */
    @GetMapping(Constants.PAGE_PATH)
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks(@PathVariable(Constants.PAGE_NUMBER) int pageNumber,
                                                             @PathVariable(Constants.TOTAL_ROWS) int totalRows) {
        return new ResponseEntity<>(feedbackService.getFeedbacks(pageNumber, totalRows), HttpStatus.OK);
    }

    /**
     * <p>
     * update feedback details
     * </p>
     *
     * @param feedbackDto - a dto that contains information to update
     * @return FeedbackDto as ResponseEntity
     */
    @PutMapping
    public ResponseEntity<FeedbackDto> updateFeedback(@RequestBody FeedbackDto feedbackDto) {
        return new ResponseEntity<>(feedbackService.updateFeedback(feedbackDto), HttpStatus.OK);
    }

    /**
     * <p>
     * get feedback details
     * </p>
     *
     * @param id - an integer that refers id on the database
     * @return FeedbackDto as ResponseEntity
     */
    @GetMapping(Constants.ID)
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(feedbackService.getFeedbackById(id), HttpStatus.OK);
    }

    /**
     * <p>
     * insert Feedback details
     * </p>
     *
     * @param feedbackDto - a dto object that contains information
     * @return FeedbackDto as ResponseEntity
     */
    @PostMapping
    public ResponseEntity<FeedbackDto> insertFeedback(@RequestBody FeedbackDto feedbackDto) {
        feedbackDto.setStatus(Constants.ACTIVE);
        return new ResponseEntity<>(feedbackService.addFeedback(feedbackDto), HttpStatus.OK);
    }

    /**
     * <p>
     * delete Feedback details
     * </p>
     *
     * @param id is an integer that refer id in database
     * @return String as ResponseEntity
     */
    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteFeedbackById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(feedbackService.deleteFeedback(id), HttpStatus.OK);
    }
}

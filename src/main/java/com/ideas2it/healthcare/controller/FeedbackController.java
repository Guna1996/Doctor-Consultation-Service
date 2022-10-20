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
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    /**
     * gets all Feedback details
     *
     * @param pageNumber - page number to show
     * @param totalRows - a set of rows to be shown
     * @return ResponseEntity
     */
    @GetMapping("/{pageNumber}/{totalRows}")
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks(@PathVariable("pageNumber") int pageNumber
            , @PathVariable("totalRows") int totalRows) {
        return new ResponseEntity<>(feedbackService.getFeedbacks(pageNumber, totalRows), HttpStatus.OK);
    }

    /**
     * update feedback details
     *
     * @param feedbackDto - a dto that contains information to update
     * @return ResponseEntity
     */
    @PutMapping
    public ResponseEntity<FeedbackDto> updateFeedback(@RequestBody FeedbackDto feedbackDto) {
        return new ResponseEntity<>(feedbackService.updateFeedback(feedbackDto),HttpStatus.OK);
    }

    /**
     * get feedback details
     *
     * @param id - an integer that refers id on the database
     * @return ResponseEntity
     */
    @GetMapping(Constants.ID)
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(feedbackService.getFeedbackById(id), HttpStatus.OK);
    }

    /**
     * insert Feedback details
     *
     * @param feedbackDto - a dto object that contains information
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<FeedbackDto> insertFeedback(@RequestBody FeedbackDto feedbackDto) {
        feedbackDto.setStatus(Constants.ACTIVE);
        return new ResponseEntity<>(feedbackService.addFeedback(feedbackDto),HttpStatus.OK);
    }

    /**
     * delete Feedback details
     *
     * @param id - a integer that refer id in database
     * @return ResponseEntity
     */
    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteFeedbackById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(feedbackService.deleteFeedback(id), HttpStatus.OK);
    }

}

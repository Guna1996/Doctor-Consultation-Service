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

    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks() {
        return new ResponseEntity<>(feedbackService.getFeedbacks(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<FeedbackDto> updateFeedback(@RequestBody FeedbackDto feedbackDto) {
        return new ResponseEntity<>(feedbackService.updateFeedback(feedbackDto),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable("id") int id) {
        return new ResponseEntity<>(feedbackService.getFeedbackById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FeedbackDto> insertFeedback(@RequestBody FeedbackDto feedbackDto) {
        feedbackDto.setStatus(Constants.ACTIVE);
        return new ResponseEntity<>(feedbackService.addFeedback(feedbackDto),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteFeedbackById(@PathVariable("id") int id) {
        return new ResponseEntity<>(feedbackService.deleteFeedback(id), HttpStatus.OK);
    }

}

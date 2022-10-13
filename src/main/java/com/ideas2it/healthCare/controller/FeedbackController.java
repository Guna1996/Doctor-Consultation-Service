package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.FeedbackDto;
import com.ideas2it.healthCare.service.FeedbackService;

import lombok.RequiredArgsConstructor;
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
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackService.getFeedbacks();
    }

    @PutMapping
    public FeedbackDto updateFeedback(@RequestBody FeedbackDto feedbackDto) {
        return feedbackService.updateFeedback(feedbackDto);
    }

    @GetMapping(path = "/{id}")
    public FeedbackDto getFeedbackById(@PathVariable("id") int id) {
        return feedbackService.getFeedbackById(id);
    }

    @PostMapping
    public FeedbackDto insertFeedback(@RequestBody FeedbackDto feedbackDto) {
        feedbackDto.setStatus(Constants.ACTIVE);
        return feedbackService.addFeedback(feedbackDto);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean deleteById(@PathVariable("id") int id) {
        return feedbackService.deleteFeedback(id);
    }

}

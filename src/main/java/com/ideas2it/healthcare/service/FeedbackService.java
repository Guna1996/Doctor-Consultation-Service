package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.FeedbackDto;

import java.util.List;

public interface  FeedbackService {
    FeedbackDto addFeedback(FeedbackDto feedbackDto);

    FeedbackDto updateFeedback(FeedbackDto feedbackDto);

    FeedbackDto getFeedbackById(int id);

    List<FeedbackDto> getFeedbacks();

    String deleteFeedback(int id);

}

package com.ideas2it.doctorConsultancyService.service;

import com.ideas2it.doctorConsultancyService.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    FeedbackDto addFeedback(FeedbackDto feedbackDto);

    FeedbackDto updateFeedback(FeedbackDto feedbackDto);

    FeedbackDto getFeedbackById(int id);

    List<FeedbackDto> getFeedbacks();

    Boolean deleteFeedback(int id);

}

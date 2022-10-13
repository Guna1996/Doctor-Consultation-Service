package com.ideas2it.doctorConsultancyService.service;

import com.ideas2it.doctorConsultancyService.dto.FeedbackDto;
import com.ideas2it.doctorConsultancyService.exception.NotFoundException;

import java.util.List;

public interface  FeedbackService {
    FeedbackDto addFeedback(FeedbackDto feedbackDto);

    FeedbackDto updateFeedback(FeedbackDto feedbackDto) throws NotFoundException;

    FeedbackDto getFeedbackById(int id) throws NotFoundException;

    List<FeedbackDto> getFeedbacks() throws NotFoundException;

    Boolean deleteFeedback(int id) throws NotFoundException;

}

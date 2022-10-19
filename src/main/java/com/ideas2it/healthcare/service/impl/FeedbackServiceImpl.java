package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.FeedbackMapper;
import com.ideas2it.healthcare.model.Feedback;
import com.ideas2it.healthcare.repo.FeedbackRepo;
import com.ideas2it.healthcare.service.FeedbackService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepo;

    public FeedbackServiceImpl(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    public FeedbackDto addFeedback(FeedbackDto feedbackDto) {
        return FeedbackMapper.toDto(feedbackRepo.save(FeedbackMapper.fromDto(feedbackDto)));
    }

    public FeedbackDto updateFeedback(FeedbackDto feedbackDto) {
        FeedbackDto feedbackDtoToReturn = null;
        if (feedbackRepo.existsByIdAndStatus(feedbackDto.getId(), feedbackDto.getStatus())) {
            feedbackDtoToReturn = FeedbackMapper.toDto(feedbackRepo.save(FeedbackMapper.fromDto(feedbackDto)));
        }
        else {
            throw new NotFoundException("The data doesn't exist");
        }
        return feedbackDtoToReturn;
    }

    public FeedbackDto getFeedbackById(int id){
        Feedback feedback = feedbackRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("feedback not found"));
        return FeedbackMapper.toDto(feedback);
    }

    public List<FeedbackDto> getFeedbacks() {
        List<FeedbackDto> feedbacksDto = null;
        List<Feedback> feedbacks = feedbackRepo.findAllByStatus(Constants.ACTIVE);
        if (!feedbacks.isEmpty()) {
            feedbacksDto = new ArrayList<>();
            for (Feedback feedback : feedbacks) {
                feedbacksDto.add(FeedbackMapper.toDto(feedback));
            }
        }
        else {
            throw new NotFoundException("Data is empty");
        }
        return feedbacksDto;
    }

    public String deleteFeedback(int id) {
        Feedback feedback = feedbackRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Feedback not found"));
        feedback.setStatus(Constants.INACTIVE);
        feedbackRepo.save(feedback);
        return "Deleted Successfully";
    }

}

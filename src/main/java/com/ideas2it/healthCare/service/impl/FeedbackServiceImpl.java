package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.common.ErrorConstants;
import com.ideas2it.healthCare.common.UserConstants;
import com.ideas2it.healthCare.dto.FeedbackDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.FeedbackMapper;
import com.ideas2it.healthCare.model.Feedback;
import com.ideas2it.healthCare.repo.FeedbackRepo;
import com.ideas2it.healthCare.service.FeedbackService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepo;

    public FeedbackServiceImpl(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    @Override
    public FeedbackDto addFeedback(FeedbackDto feedbackDto) {
        return FeedbackMapper.toDto(feedbackRepo.save(FeedbackMapper.fromDto(feedbackDto)));
    }

    @Override
    public FeedbackDto updateFeedback(FeedbackDto feedbackDto) {
        if (feedbackRepo.existsByIdAndStatus(feedbackDto.getId(), feedbackDto.getStatus())) {
            return FeedbackMapper.toDto(feedbackRepo.save(FeedbackMapper.fromDto(feedbackDto)));
        }
        else {
            throw new NotFoundException(UserConstants.DATA_DOES_NOT_EXIST);
        }
    }

    @Override
    public FeedbackDto getFeedbackById(int id){
        Feedback feedback = feedbackRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(UserConstants.FEEDBACK_NOT_FOUND));
        return FeedbackMapper.toDto(feedback);
    }

    @Override
    public List<FeedbackDto> getFeedbacks() {
        List<Feedback> feedbacks = feedbackRepo.findAllByStatus(Constants.ACTIVE);
        if (!feedbacks.isEmpty()) {
            List<FeedbackDto> feedbacksDto = new ArrayList<>();
            for (Feedback feedback : feedbacks) {
                feedbacksDto.add(FeedbackMapper.toDto(feedback));
            }
            return feedbacksDto;
        }
        else {
            throw new NotFoundException(UserConstants.DATA_IS_EMPTY);
        }
    }

    @Override
    public String deleteFeedback(int id) {
        Feedback feedback = feedbackRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(UserConstants.FEEDBACK_NOT_FOUND));
        feedback.setStatus(Constants.INACTIVE);
        feedbackRepo.save(feedback);
        return UserConstants.DELETED_SUCCESSFULLY;
    }

}

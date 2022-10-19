package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.FeedbackDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.FeedbackMapper;
import com.ideas2it.healthCare.model.Feedback;
import com.ideas2it.healthCare.repo.FeedbackRepo;
import com.ideas2it.healthCare.service.FeedbackService;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

    public List<FeedbackDto> getFeedbacks(int pageNumber, int totalRows) {
        List<FeedbackDto> feedbacksDto = null;
        List<Feedback> feedbacks = feedbackRepo.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
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

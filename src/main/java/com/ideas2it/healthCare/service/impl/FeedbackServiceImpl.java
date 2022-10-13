package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.FeedbackDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.model.Feedback;
import com.ideas2it.healthCare.repo.FeedbackRepo;
import com.ideas2it.healthCare.service.FeedbackService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    private final ModelMapper modelMapper;

    @Override
    public FeedbackDto addFeedback(FeedbackDto feedbackDto) {
        return modelMapper.map(feedbackRepo.save(modelMapper.map(feedbackDto, Feedback.class)), FeedbackDto.class);
    }

    @Override
    public FeedbackDto updateFeedback(FeedbackDto feedbackDto) {
        if (feedbackRepo.existsByIdAndStatus(feedbackDto.getId(), feedbackDto.getStatus())) {
            return modelMapper.map(feedbackRepo.save(modelMapper.map(feedbackDto, Feedback.class)),FeedbackDto.class);
        }
        else {
            throw new NotFoundException("The data doesn't exist");
        }
    }

    @Override
    public FeedbackDto getFeedbackById(int id){
        Feedback feedback = feedbackRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("feedback not found"));
        return modelMapper.map(feedback, FeedbackDto.class);
    }

    @Override
    public List<FeedbackDto> getFeedbacks() {
        List<Feedback> feedbacks = feedbackRepo.findAllByStatus(Constants.ACTIVE);
        if (!feedbacks.isEmpty()) {
            List<FeedbackDto> feedbacksDto = new ArrayList<>();
            for (Feedback feedback : feedbacks) {
                feedbacksDto.add(modelMapper.map(feedback, FeedbackDto.class));
            }
            return feedbacksDto;
        }
        else {
            throw new NotFoundException("Data is empty");
        }
    }

    @Override
    public String deleteFeedback(int id) {
        Feedback feedback = feedbackRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Feedback not found"));
        feedback.setStatus(Constants.INACTIVE);
        feedbackRepo.save(feedback);
        return "Deleted Successfully";
    }

}

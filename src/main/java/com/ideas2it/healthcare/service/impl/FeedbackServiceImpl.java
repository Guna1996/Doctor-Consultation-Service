/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.UserConstants;
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.FeedbackMapper;
import com.ideas2it.healthcare.model.Feedback;
import com.ideas2it.healthcare.repo.FeedbackRepository;
import com.ideas2it.healthcare.service.FeedbackService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This FeedbackServiceImpl class is a service class this class implements
 * FeedbackService which is an interface and get information from
 * the repository
 * </p>
 *
 * @author Bala Ashwanth
 * @since 2022-10-10
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * {@inheritDoc}
     */
    public FeedbackDto addFeedback(FeedbackDto feedbackDto) {
        return FeedbackMapper.toDto(feedbackRepository.save(FeedbackMapper.fromDto(feedbackDto)));
    }

    /**
     * {@inheritDoc}
     */
    public FeedbackDto updateFeedback(FeedbackDto feedbackDto) {
        FeedbackDto feedbackDtoToReturn = null;
        if (feedbackRepository.existsByIdAndStatus(feedbackDto.getId(), feedbackDto.getStatus())) {
            feedbackDtoToReturn = FeedbackMapper.toDto(feedbackRepository.save(FeedbackMapper.fromDto(feedbackDto)));
        } else {
            throw new NotFoundException(UserConstants.DATA_DOES_NOT_EXIST);
        }
        return feedbackDtoToReturn;
    }

    /**
     * {@inheritDoc}
     */
    public FeedbackDto getFeedbackById(int id) {
        Feedback feedback = feedbackRepository.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(UserConstants.FEEDBACK_NOT_FOUND));
        return FeedbackMapper.toDto(feedback);
    }

    /**
     * {@inheritDoc}
     */
    public List<FeedbackDto> getFeedbacks(int pageNumber, int totalRows) {
        List<FeedbackDto> feedbacksDto = null;
        List<Feedback> feedbacks = feedbackRepository.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (!feedbacks.isEmpty()) {
            feedbacksDto = new ArrayList<>();
            for (Feedback feedback : feedbacks) {
                feedbacksDto.add(FeedbackMapper.toDto(feedback));
            }
        } else {
            throw new NotFoundException(UserConstants.DATA_IS_EMPTY);
        }
        return feedbacksDto;
    }

    /**
     * {@inheritDoc}
     */
    public String deleteFeedback(int id) {
        if (feedbackRepository.deleteSpecializationById(id) == 1) {
            return UserConstants.DELETED_SUCCESSFULLY;
        }
        return ErrorConstants.FEEDBACK_NOT_FOUND;
    }

}

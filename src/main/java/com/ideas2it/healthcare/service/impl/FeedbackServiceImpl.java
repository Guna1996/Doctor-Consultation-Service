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
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.FeedbackMapper;
import com.ideas2it.healthcare.model.Feedback;
import com.ideas2it.healthcare.repo.FeedbackRepository;
import com.ideas2it.healthcare.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private FeedbackRepository feedbackRepository;


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
        return FeedbackMapper.toDto(feedbackRepository.save(FeedbackMapper.fromDto(feedbackDto)));
    }

    /**
     * {@inheritDoc}
     */
    public FeedbackDto getFeedbackById(int id) {
        return feedbackRepository.findByIdAndStatus(id, Constants.ACTIVE)
                .stream()
                .map(FeedbackMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(MessageConstants.FEEDBACK_NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */
    public String deleteFeedback(int id) {
        if (feedbackRepository.deleteSpecializationById(id) == 1) {
            return ErrorConstants.FEEDBACK_DELETED_SUCCESSFULLY;
        }
        return ErrorConstants.FEEDBACK_NOT_FOUND;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FeedbackDto> getFeedbackByDoctorId(int doctorId, int pageNumber, int totalRows) {
        return feedbackRepository.findByDoctorIdAndStatus(doctorId, Constants.ACTIVE, PageRequest
                .of(pageNumber, totalRows)).toList().stream().map(FeedbackMapper::toDto).collect(Collectors.toList());
    }

}

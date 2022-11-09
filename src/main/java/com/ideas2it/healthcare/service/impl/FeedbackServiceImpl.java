/**
 * <p>
 * This package contains classes are Doctor Clinic Impl,
 * Patient Impl, Doctor Impl, Clinic Impl,
 * Appointment Impl, Feedback Impl, Specialization Impl,
 * Timeslot Impl, Patient Vital Impl
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.FeedbackDto;
import com.ideas2it.healthcare.mapper.FeedbackMapper;
import com.ideas2it.healthcare.repository.FeedbackRepository;
import com.ideas2it.healthcare.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * This Feedback Service Impl class is a service class this class implements
 * Feedback Service which is an interface and get information from
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
    public String addFeedback(FeedbackDto feedbackDto) {
        feedbackRepository.save(FeedbackMapper.fromDto(feedbackDto));
        return MessageConstants.FEEDBACK_ADDED_SUCCESSFULLY;
    }

    /**
     * {@inheritDoc}
     */
    public String deleteFeedback(Integer id) {
        String response = null;
        if (1 <= feedbackRepository.removeFeedbackById(id)) {
            response = MessageConstants.FEEDBACK_REMOVED_SUCCESSFULLY;
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FeedbackDto> getFeedbacksByDoctorId(Integer doctorId, Integer pageNumber,
                                                    Integer totalRows) {
        return feedbackRepository.findByDoctorIdAndStatus(doctorId, Constants.ACTIVE, PageRequest
                        .of(pageNumber, totalRows)).toList()
                .stream().map(FeedbackMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    public Integer getFeedbacksCountByDoctorId(Integer doctorId) {
        return feedbackRepository.countByDoctorIdAndStatus(doctorId, Constants.ACTIVE);
    }
}

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
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.exception.SqlException;
import com.ideas2it.healthcare.mapper.FeedbackMapper;
import com.ideas2it.healthcare.repository.FeedbackRepository;
import com.ideas2it.healthcare.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            feedbackRepository.save(FeedbackMapper.fromDto(feedbackDto));
            return MessageConstants.FEEDBACK_ADDED_SUCCESSFULLY;
        } catch (Exception exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String deleteFeedback(Integer id) {
        try {
            if (1 <= feedbackRepository.removeFeedbackById(id)) {
                return MessageConstants.FEEDBACK_REMOVED_SUCCESSFULLY;
            }
            throw new NotFoundException(ErrorConstants.FEEDBACK_NOT_FOUND);
        } catch (Exception exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FeedbackDto> getFeedbackByDoctorId(Integer doctorId, Integer pageNumber,
                                                   Integer totalRows) {
        try {
            return feedbackRepository.findByDoctorIdAndStatus(doctorId, Constants.ACTIVE, PageRequest
                            .of(pageNumber, totalRows)).toList()
                    .stream().map(FeedbackMapper::toDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer getFeedbacksCountByDoctorId(Integer doctorId) {
        try {
            return feedbackRepository.countByDoctorIdAndStatus(doctorId, Constants.ACTIVE);
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }
}

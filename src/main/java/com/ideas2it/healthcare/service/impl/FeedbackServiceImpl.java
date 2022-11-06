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
        try {
            return FeedbackMapper.toDto(feedbackRepository.save(FeedbackMapper.fromDto(feedbackDto)));
        } catch (Exception exception) {
            throw new SqlException(exception.getMessage());
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
            throw new SqlException(exception.getMessage());
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
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer getFeedbacksCountByDoctorId(Integer doctorId) {
        try {
            return feedbackRepository.countByDoctorIdAndStatus(doctorId, Constants.ACTIVE);
        } catch (DataAccessException exception) {
            throw new SqlException(exception.getMessage());
        }
    }
}

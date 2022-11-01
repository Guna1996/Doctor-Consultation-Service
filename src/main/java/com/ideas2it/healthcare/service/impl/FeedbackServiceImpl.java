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
import com.ideas2it.healthcare.mapper.FeedbackMapper;
import com.ideas2it.healthcare.repo.FeedbackRepository;
import com.ideas2it.healthcare.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Double totalPages = 0.0;
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
    public String deleteFeedback(Integer id) {
        if (1 <= feedbackRepository.deleteSpecializationById(id)) {
            return MessageConstants.FEEDBACK_DELETED_SUCCESSFULLY;
        }
        return ErrorConstants.FEEDBACK_NOT_FOUND;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FeedbackDto> getFeedbackByDoctorId(Integer doctorId, Integer pageNumber,
                                                   Integer totalRows) {
        setTotalPages(Math.floor((feedbackRepository
                .findByDoctorIdAndStatus(doctorId, Constants.ACTIVE).size() + 0.0/totalRows)));
        return feedbackRepository.findByDoctorIdAndStatus(doctorId, Constants.ACTIVE, PageRequest
                .of(pageNumber, totalRows)).toList()
                .stream().map(FeedbackMapper::toDto)
                .collect(Collectors.toList());
    }

    public Double getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Double totalPages) {
        this.totalPages = totalPages;
    }
}

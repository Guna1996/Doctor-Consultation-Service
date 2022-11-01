/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * </p>
 * <p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.TimeslotMapper;
import com.ideas2it.healthcare.model.Timeslot;
import com.ideas2it.healthcare.repo.TimeslotRepository;
import com.ideas2it.healthcare.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * This TimeslotServiceImpl class is a service class this class implements
 * TimeslotService which is an interface and get information from
 * the repository
 * </p>
 *
 * @author Bala Ashwanth
 * @since 2022-10-10
 */
@Service
public class TimeslotServiceImpl implements TimeslotService {

    private Double totalPages = 0.0;
    @Autowired
    private TimeslotRepository timeslotRepository;

    /**
     * {@inheritDoc}
     */
    public TimeslotDto addTimeslot(TimeslotDto timeslotDto) {
        return TimeslotMapper.toDto(timeslotRepository
                .save(TimeslotMapper.fromDto(timeslotDto)));
    }

    /**
     * {@inheritDoc}
     */
    public List<TimeslotDto> getTimeslots(Integer pageNumber, Integer totalRows) {
        setTotalPages(Math.floor((timeslotRepository.findAll().size() + 0.0 / totalRows)));
        List<Timeslot> timeslots = timeslotRepository
                .findAll(PageRequest.of(pageNumber, totalRows)).toList();
        if (timeslots.isEmpty()) {
            throw new NotFoundException(MessageConstants.TIMESLOT_IS_EMPTY);
        }
        return timeslots.stream().map(TimeslotMapper::toDto)
                .collect(Collectors.toList());
    }

    public Double getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Double totalPages) {
        this.totalPages = totalPages;
    }
}

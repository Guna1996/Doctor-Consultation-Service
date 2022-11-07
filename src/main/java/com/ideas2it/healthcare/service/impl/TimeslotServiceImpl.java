/**
 * <p>
 * This package contains classes are Doctor Clinic Impl,
 * Patient Impl, Doctor Impl, Clinic Impl,
 * Appointment Impl, Feedback Impl, Specialization Impl,
 * Timeslot Impl, Patient Vital Impl
 * <p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.exception.SqlException;
import com.ideas2it.healthcare.mapper.TimeslotMapper;
import com.ideas2it.healthcare.model.Timeslot;
import com.ideas2it.healthcare.repository.TimeslotRepository;
import com.ideas2it.healthcare.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * This Timeslot Service Impl class is a service class this class implements
 * Timeslot Service which is an interface and get information from
 * the repository
 * </p>
 *
 * @author Bala Ashwanth
 * @since 2022-10-10
 */
@Service
public class TimeslotServiceImpl implements TimeslotService {

    @Autowired
    private TimeslotRepository timeslotRepository;

    /**
     * {@inheritDoc}
     */
    public TimeslotDto addTimeslot(TimeslotDto timeslotDto) {
        try {
            return TimeslotMapper.toDto(timeslotRepository
                    .save(TimeslotMapper.fromDto(timeslotDto)));
        } catch (DataIntegrityViolationException exception) {
            throw new NotFoundException(ErrorConstants.TIMESLOT_ALREADY_EXISTS);
        } catch (DataAccessException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TimeslotDto> getTimeslots(Integer pageNumber, Integer totalRows) {
        try {
            List<Timeslot> timeslots = timeslotRepository
                    .findAll(PageRequest.of(pageNumber, totalRows)).toList();
            if (timeslots.isEmpty()) {
                throw new NotFoundException(MessageConstants.TIMESLOT_IS_EMPTY);
            }
            return timeslots.stream().map(TimeslotMapper::toDto)
                    .collect(Collectors.toList());
        } catch (SqlException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer getTimeslotsCount() {
        try {
            return (int) timeslotRepository.count();
        } catch (DataAccessException exception) {
            throw new SqlException(exception.getMessage());
        }
    }

    public boolean isValidTimeslot(LocalTime localTime) {
        Timeslot timeslot = timeslotRepository.findByTimeslot(localTime);
        if (timeslot == null) {
            System.out.println(localTime);
            return false;
        }
        return timeslot.getTimeslot().equals(localTime);
    }
}

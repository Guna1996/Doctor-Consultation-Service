/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * <p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.sql.SQLException;
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

    @Autowired
    private TimeslotRepository timeslotRepository;

    /**
     * {@inheritDoc}
     */
    public TimeslotDto addTimeslot(TimeslotDto timeslotDto) {
        if (!isValidTimeslot(timeslotDto)) {
            throw new NotFoundException(ErrorConstants.TIMESLOT_ALREADY_EXISTS);
        }
        try {
            return TimeslotMapper.toDto(timeslotRepository
                    .save(TimeslotMapper.fromDto(timeslotDto)));
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
        } catch (DataAccessException exception) {
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

    /**
     * <p>
     * This method will check the given timeslot is already
     * available and returns a boolean value
     * </p>
     *
     * @param timeslotDto {@link TimeslotDto}
     * @return {@link Boolean}
     */
    private Boolean isValidTimeslot(TimeslotDto timeslotDto) {
        if (12 < timeslotDto.getTimeslot().getHour()) {
            throw new NotFoundException(ErrorConstants.INVALID_TIMESLOT);
        }
        List<Timeslot> timeslots = timeslotRepository.findAll();
        for (Timeslot timeslot: timeslots) {
            if (timeslot.getTimeslot().getHour() == timeslotDto.getTimeslot().getHour() &&
               (timeslot.getTimeslot().getMinute() == timeslotDto.getTimeslot().getMinute()) &&
               (timeslot.getTimeslot().getSecond() == timeslotDto.getTimeslot().getSecond()) &&
               (timeslot.getTimeFormat().equals(timeslotDto.getTimeFormat()))) {
                    return false;
            }
        }
        return true;
    }
}

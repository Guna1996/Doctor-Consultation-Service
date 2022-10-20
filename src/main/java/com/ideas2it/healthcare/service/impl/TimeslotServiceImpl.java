/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.UserConstants;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.TimeslotMapper;
import com.ideas2it.healthcare.model.Timeslot;
import com.ideas2it.healthcare.repo.TimeslotRepository;
import com.ideas2it.healthcare.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This TimeslotServiceImpl class is a service class this class implements
 * TimeslotService which is an interface and get information from
 * the repository
 * </p>
 *
 * @author  Bala Ashwanth
 *
 * @since   2022-10-10
 */
@Service
public class TimeslotServiceImpl implements TimeslotService {

    @Autowired
    private TimeslotRepository timeslotRepository;

    /**
     * {@inheritDoc}
     */
    public TimeslotDto addTimeslot(TimeslotDto timeslotDto) {
        return TimeslotMapper.toDto(timeslotRepository.save(TimeslotMapper.fromDto(timeslotDto)));
    }

    /**
     * {@inheritDoc}
     */
    public TimeslotDto updateTimeslot(TimeslotDto timeslotDto) {
        TimeslotDto timeslotDtoToReturn = null;
        if (timeslotRepository.existsById(timeslotDto.getId())) {
            timeslotDtoToReturn = TimeslotMapper.toDto(timeslotRepository.save(TimeslotMapper.fromDto(timeslotDto)));
        }
        else {
            throw new NotFoundException(UserConstants.DATA_DOES_NOT_EXIST);
        }
        return timeslotDtoToReturn;
    }

    /**
     * {@inheritDoc}
     */
    public TimeslotDto getTimeslotById(int id) {
        Timeslot timeslot = timeslotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserConstants.TIMESLOT_NOT_FOUND));
        return TimeslotMapper.toDto(timeslot);
    }

    /**
     * {@inheritDoc}
     */
    public List<TimeslotDto> getTimeslots(int pageNumber, int totalRows) {
        List<TimeslotDto> timeslotsDto = null;
        List<Timeslot> timeslots = timeslotRepository.findAll(PageRequest.of(pageNumber, totalRows)).toList();
        if (!timeslots.isEmpty()) {
            timeslotsDto = new ArrayList<>();
            for (Timeslot timeslot : timeslots) {
                timeslotsDto.add(TimeslotMapper.toDto(timeslot));
            }
        }
        else {
            throw new NotFoundException(UserConstants.DATA_IS_EMPTY);
        }
        return timeslotsDto;
    }

    /**
     * {@inheritDoc}
     */
    public String deleteTimeslot(int id) {
        if (timeslotRepository.existsById(id)) {
            timeslotRepository.deleteById(id);
            return UserConstants.DELETED_SUCCESSFULLY;
        }
        return ErrorConstants.TIMESLOT_NOT_FOUND;
    }
}

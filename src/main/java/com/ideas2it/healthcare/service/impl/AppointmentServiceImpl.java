/**
 * <p>
 * This package contains classes are Doctor clinic Impl,
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
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.exception.SqlException;
import com.ideas2it.healthcare.mapper.AppointmentMapper;
import com.ideas2it.healthcare.repository.AppointmentRepository;
import com.ideas2it.healthcare.service.AppointmentService;
import com.ideas2it.healthcare.service.TimeslotService;
import com.ideas2it.healthcare.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * Appointment service Impl class implements Appointment service
 * and it contains methods and with helps of passing object to
 * AppointmentRepository interface
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TimeslotService timeslotService;

    /**
     * {@inheritDoc}
     */
    public String addAppointment(AppointmentDto appointmentDto) {
        if (DateUtil.isDateValid(appointmentDto.getScheduledOn())
                && timeslotService.isValidTimeslot(appointmentDto.getScheduledOn().toLocalTime(), appointmentDto.getTimeFormat())) {
            return saveAppointment(appointmentDto);
        }
        throw new NotFoundException(ErrorConstants.ENTER_VALID_DATE_TIME);
    }

    /**
     * {@inheritDoc}
     */
    public Boolean isAppointmentAvailable(Integer id, LocalDateTime dateTime) {
        try {
            return appointmentRepository
                    .findByDoctorIdAndScheduledOnAndStatus(id, dateTime, Constants.ACTIVE).isEmpty();
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String removeAppointmentById(Integer id) {
        try {
            if (1 <= appointmentRepository.removeAppointmentById(id)) {
                return MessageConstants.APPOINTMENT_REMOVED_SUCCESSFULLY;
            }
            throw new NotFoundException(ErrorConstants.APPOINTMENT_NOT_FOUND);
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<AppointmentDto> getAppointmentsByPatientId(Integer patientId, Integer pageNumber,
                                                           Integer totalRows) {
        try {
            return appointmentRepository.findByPatientIdAndStatus(patientId, Constants.ACTIVE,
                            PageRequest.of(pageNumber, totalRows))
                    .stream()
                    .map(AppointmentMapper::toDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<AppointmentDto> getAppointmentsByDoctorId(Integer doctorId, Integer pageNumber,
                                                          Integer totalRows) {
        try {
            return appointmentRepository
                    .findByDoctorIdAndStatus(doctorId, Constants.ACTIVE, PageRequest.of(pageNumber,
                            totalRows))
                    .toList().stream()
                    .map(AppointmentMapper::toDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer getCountOfAppointmentByPatientId(Integer patientId) {
        try {
            return appointmentRepository.countByPatientIdAndStatus(patientId, Constants.ACTIVE);
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Integer getCountOfAppointmentByDoctorId(Integer doctorId) {
        try {
            return appointmentRepository.countByDoctorIdAndStatus(doctorId, Constants.ACTIVE);
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String rescheduleAppointment(AppointmentDto appointmentDto) {
        LocalDate date = appointmentDto.getScheduledOn().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if (DateUtil.isDateValid(appointmentDto.getScheduledOn())
                && timeslotService.isValidTimeslot(appointmentDto.getScheduledOn().toLocalTime(), appointmentDto.getTimeFormat())) {
            saveAppointment(appointmentDto);
            return MessageConstants.APPOINTMENT_RESCHEDULED_SUCCESSFULLY;
        }
        throw new NotFoundException(ErrorConstants.ENTER_VALID_DATE_TIME);
    }

    /**
     * {@inheritDoc}
     */
    public String saveAppointment(AppointmentDto appointmentDto) {
        try {
            if (!isAppointmentAvailable(appointmentDto.getDoctor().getId(),
                    appointmentDto.getScheduledOn())) {
                throw new NotFoundException(ErrorConstants.APPOINTMENT_NOT_AVAILABLE_FOR_THIS_SCHEDULE);
            }
            appointmentRepository.save(AppointmentMapper.fromDto(appointmentDto));
            return MessageConstants.APPOINTMENT_ADDED_SUCCESSFULLY;
        } catch (DataAccessException exception) {
            throw new SqlException(ErrorConstants.CANNOT_ACCESS_DATABASE);
        }
    }
}

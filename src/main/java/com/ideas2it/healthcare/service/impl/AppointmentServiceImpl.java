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
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.AppointmentMapper;
import com.ideas2it.healthcare.repo.AppointmentRepository;
import com.ideas2it.healthcare.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * Appointment serviceImpl class implements Appointment service
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

    /**
     * {@inheritDoc}
     */
    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        LocalDate date = appointmentDto.getScheduledOn().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if (Period.between(date, currentDate).getDays() > 0) {
            throw new NotFoundException(ErrorConstants.ENTER_VALID_DATE_TIME);
        }
        return saveAppointment(appointmentDto);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAppointmentAvailable(int id, LocalDateTime dateTime) {
        return appointmentRepository
                .findByDoctorIdAndScheduledOnAndStatus(id, dateTime, Constants.ACTIVE)
                .isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public String deleteAppointmentById(int id) {
        if (appointmentRepository.deleteAppointmentById(id) == 1) {
            return ErrorConstants.APPOINTMENT_DELETED_SUCCESSFULLY;
        }
        return ErrorConstants.APPOINTMENT_NOT_FOUND;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AppointmentDto> getAppointmentsByPatientId(int patientId, int pageNumber, int totalRows) {
        return appointmentRepository.findByPatientIdAndStatus(
                patientId, Constants.ACTIVE, PageRequest.of(pageNumber, totalRows))
                .toList().stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AppointmentDto> getAppointmentsByDoctorId(int doctorId, int pageNumber, int totalRows) {
        return appointmentRepository
                .findByDoctorIdAndStatus(doctorId, Constants.ACTIVE, PageRequest.of(pageNumber, totalRows))
                .toList().stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    public AppointmentDto rescheduleAppointment(AppointmentDto appointmentDto) {
        LocalDate date = appointmentDto.getScheduledOn().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if (Period.between(date, currentDate).getDays() > 0) {
            throw new NotFoundException(ErrorConstants.ENTER_VALID_DATE_TIME);
        }
        return saveAppointment(appointmentDto);
    }

    /**
     * {@inheritDoc}
     */
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        if (!isAppointmentAvailable(appointmentDto.getDoctor().getId(),
                appointmentDto.getScheduledOn())) {
            throw new NotFoundException(ErrorConstants.APPOINTMENT_NOT_AVAILABLE_FOR_THIS_SCHEDULE);
        }
        return AppointmentMapper
                .toDto(appointmentRepository.save(AppointmentMapper.fromDto(appointmentDto)));
    }
}

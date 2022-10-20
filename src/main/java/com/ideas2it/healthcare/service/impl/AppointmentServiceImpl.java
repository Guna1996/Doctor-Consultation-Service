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

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.UserConstants;
import com.ideas2it.healthcare.dto.AppointmentDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.AppointmentMapper;
import com.ideas2it.healthcare.model.Appointment;
import com.ideas2it.healthcare.repo.AppointmentRepository;
import com.ideas2it.healthcare.service.AppointmentService;
import com.ideas2it.healthcare.service.ClinicService;
import com.ideas2it.healthcare.service.DoctorService;
import com.ideas2it.healthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * <p>
 * AppointmentserviceImpl class implements Appointmentservice
 * and it contains methods and with helps of passing object to
 * AppointmentRepository interface
 * </p>
 *
 * @author Gunaseelan K
 *
 * @version 1
 *
 * @since 2022-10-18
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicService clinicService;

    /**
     * {@inheritDoc}
     */
    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        LocalDate date = appointmentDto.getScheduledOn().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if (Period.between(date, currentDate).getDays() < 0) {
            if (doctorService.isDoctorAvailable(appointmentDto.getDoctor().getId())
                    && patientService.isPatientAvailable(appointmentDto.getPatient().getId())
                    && clinicService.isClinicAvailable(appointmentDto.getClinic().getId())) {
                return saveAppointment(appointmentDto);
            }
            throw new NotFoundException(ErrorConstants.DOCTOR_CLINIC_PATIENT_NOT_FOUND);
        }
        throw new NotFoundException(ErrorConstants.VALID_DATE_TIME);
    }

    /**
     * {@inheritDoc}
     */
    public List<AppointmentDto> getAppointments(int pageNumber, int totalRows) {
        List<Appointment> appointments = appointmentRepository.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (appointments.isEmpty()) {
            throw new NotFoundException(ErrorConstants.APPOINTMENT_NOT_FOUND);
        }
        return appointments.stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }


    /**
     * {@inheritDoc}
     */
    public AppointmentDto getAppointmentById(int id) {

        return appointmentRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                map(AppointmentMapper::toDto).
                findFirst().
                orElseThrow(() -> new NotFoundException(ErrorConstants.APPOINTMENT_NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAppointmentAvailable(int id, LocalDateTime dateTime) {

        return appointmentRepository.findByDoctorIdAndScheduledOnAndStatus(id, dateTime, Constants.ACTIVE).isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public String deleteAppointmentById(int id) {
        if (appointmentRepository.deleteAppointmentById(id) == 1){
            return "Deleted Successfully";
        }
        return "Doctor is not Deleted";
    }

    /**
     * {@inheritDoc}
     */
    public AppointmentDto rescheduleAppointment(AppointmentDto appointmentDto) {
        LocalDate date = appointmentDto.getScheduledOn().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        if (Period.between(date, currentDate).getDays() < 0) {
            Appointment appointment = AppointmentMapper.fromDto(appointmentDto);
            return saveAppointment(appointmentDto);
        }
        throw new NotFoundException("please enter valid date and time");
    }

    /**
     * {@inheritDoc}
     */
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        if (isAppointmentAvailable(appointmentDto.getDoctor().getId(), appointmentDto.getScheduledOn())) {
            Appointment appointment = AppointmentMapper.fromDto(appointmentDto);
            appointment.setStatus(Constants.ACTIVE);
            return AppointmentMapper.toDto(appointmentRepository.save(appointment));
        }
        throw new NotFoundException("");
    }
}

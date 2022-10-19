/**
 * <p>
 * This package contains interfaces are DoctorClinicService,
 * PatientService, DoctorService, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalsServiceImpl.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.AppointmentDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * AppointmentService interface is used for converting dto into entity
 * class and also it helps to passing objects between controller
 * to repository for CRUD operations
 * </p>
 *
 * @author  Gunaseelan K
 *
 * @version 1
 *
 * @since   2022-07-18
 */

public interface AppointmentService {

    AppointmentDto addAppointment(AppointmentDto appointmentDto);
    List<AppointmentDto> getAppointments();

    AppointmentDto getAppointmentById(int id);

    boolean isAppointmentAvailable(int id, LocalDateTime dateTime);

    AppointmentDto rescheduleAppointment(AppointmentDto appointmentDto);

    String deleteAppointmentById(int id);
}

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
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.AppointmentDto;

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
 * @since   2022-10-18
 */

public interface AppointmentService {

    /**
     * <p>
     * This abstract method is used to add appointment details
     * into data base by getting details from the
     * user
     * </p>
     *
     * @param appointmentDto - patient details
     *
     * @return patientDto
     */
    AppointmentDto addAppointment(AppointmentDto appointmentDto);

    /**
     * <p>
     *  This abstract method is used to get all appointments
     *  from the database
     *
     * </p>
     *
     * @param id - id of the appointment
     *
     * @return appointmentDto
     */
    List<AppointmentDto> getAppointments();

    /**
     * <p>
     *  This abstract method is used to get appointments
     *  from the database by getting the id and status
     *  from the user to dispaly
     * </p>
     *
     * @param id - id of the appointment
     *
     * @return appointmentDto
     */
    AppointmentDto getAppointmentById(int id);

    /**
     * <p>
     * This isAppointmentAvailable abstract method is
     * used to check wheather a appointment is available or not
     * based on the appointment for doctor in doctor database
     *
     * </p>
     *
     * @param id - id of the doctor
     * @param dateTime - date and time of appointment
     * @return boolean
     */
    boolean isAppointmentAvailable(int id, LocalDateTime dateTime);

    /**
     * <p>
     * This method is used to reschedule appointment and
     * all details into the database
     * </p>
     * @param appointmentDto - appointment detail
     *
     * @return appointmentDto
     */

    AppointmentDto rescheduleAppointment(AppointmentDto appointmentDto);

    String deleteAppointmentById(int id);
}

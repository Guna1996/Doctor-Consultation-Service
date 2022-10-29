/**
 * <p>
 * This package contains interfaces of DoctorClinicService,
 * PatientService, DoctorService, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalServiceImpl.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.AppointmentDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * AppointmentService interface consists of abstract methods which is used
 * for performing CRUD operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */

public interface AppointmentService {

    /**
     * <p>
     * This abstract method is used to add appointment details
     * into data base by getting details from the user
     * </p>
     *
     * @param appointmentDto - patient details
     * @return patientDto
     */
    AppointmentDto addAppointment(AppointmentDto appointmentDto);

    /**
     * <p>
     * This isAppointmentAvailable abstract method is
     * used to check whether a appointment is available or not
     * based on the appointment for doctor in doctor database
     * </p>
     *
     * @param id       - id of the doctor
     * @param dateTime - date and time of appointment
     * @return boolean
     */
    boolean isAppointmentAvailable(int id, LocalDateTime dateTime);

    /**
     * <p>
     * This method is used to reschedule appointment and
     * all details into the database
     * </p>
     *
     * @param appointmentDto - appointment detail
     * @return appointmentDto
     */
    AppointmentDto rescheduleAppointment(AppointmentDto appointmentDto);

    /**
     * <p>
     * This method is used to reschedule appointment and
     * all details into the database
     * </p>
     *
     * @param id is an id for unknown
     * @return String
     */
    String deleteAppointmentById(int id);

    /**
     * <p>
     * This abstract method is used to get appointments
     * from the database by patient Id
     * </p>
     *
     * @param pageNumber - Page number of the page
     * @param totalRows  - number of rows required
     * @return List<appointmentDto>
     */
    List<AppointmentDto> getAppointmentsByPatientId(int patientId, int pageNumber, int totalRows);

    /**
     * <p>
     * This abstract method is used to get appointments
     * from the database by doctor Id
     * </p>
     *
     * @param pageNumber - Page number of the page
     * @param totalRows  - number of rows required
     * @return List<appointmentDto>
     */
    List<AppointmentDto> getAppointmentsByDoctorId(int doctorId, int pageNumber, int totalRows);
}

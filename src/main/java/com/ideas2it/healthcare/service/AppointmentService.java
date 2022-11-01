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
     * This method is used to add appointment details
     * into data base by getting details from the user
     * </p>
     *
     * @param appointmentDto {@link AppointmentDto} contains appointment details
     * @return {@link AppointmentDto}
     */
    AppointmentDto addAppointment(AppointmentDto appointmentDto);

    /**
     * <p>
     * This method is used to check whether a appointment
     * is available or not based on the appointment for
     * doctor in doctor database
     * </p>
     *
     * @param id {@link Integer} is id of the doctor
     * @param dateTime {@link LocalDateTime} is date and time of appointment
     * @return {@link Boolean}
     */
    Boolean isAppointmentAvailable(Integer id, LocalDateTime dateTime);
    /**
     * <p>
     * This method is used to reschedule appointment and
     * all details into the database
     * </p>
     *
     * @param appointmentDto {@link AppointmentDto} is appointment detail
     * @return {@link AppointmentDto}
     */
    AppointmentDto rescheduleAppointment(AppointmentDto appointmentDto);

    /**
     * <p>
     * This method is used to reschedule appointment and
     * all details into the database
     * </p>
     *
     * @param id {@link Integer} is an id for unknown
     * @return {@link String}
     */
    String deleteAppointmentById(Integer id);

    /**
     * <p>
     * This method is used to get appointments
     * from the database by patient Id
     * </p>
     *
     * @param pageNumber {@link Integer} is Page number
     * @param totalRows {@link Integer} is number of rows required
     * @return {@link List<AppointmentDto>}
     */
    List<AppointmentDto> getAppointmentsByPatientId(Integer patientId, Integer pageNumber,
                                                    Integer totalRows);

    /**
     * <p>
     * This method is used to get appointments
     * from the database by doctor Id
     * </p>
     *
     * @param pageNumber {@link Integer} is Page number
     * @param totalRows {@link Integer} is number of rows required
     * @return {@link List<AppointmentDto>}
     */
    List<AppointmentDto> getAppointmentsByDoctorId(Integer doctorId, Integer pageNumber,
                                                   Integer totalRows);

    Double getTotalPages();

    void setTotalPages(Double totalPages);
}

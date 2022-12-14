/**
 * <p>
 * This package contains interfaces of DoctorClinicService,
 * Patient service, Doctor service, Clinic service,
 * Appointment service, Feedback service, Specialization service,
 * Timeslot service, Patient vital service.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.AppointmentDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Appointment service interface consists of abstract methods which is used
 * for performing crud operation. it is used to transfer objects between
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
     * This method is used to add appointment details such as
     * patient id, doctor id, clinic id and time to add
     * into data base by getting these details from the user.
     * </p>
     *
     * @param appointmentDto {@link AppointmentDto} contains appointment details
     * @return {@link String}
     */
    String addAppointment(AppointmentDto appointmentDto);

    /**
     * <p>
     * This method is used to check whether a appointment
     * is available or not based on the appointment for
     * doctor in doctor database.
     * </p>
     *
     * @param id       {@link Integer} is id of the doctor
     * @param dateTime {@link LocalDateTime} is date and time of appointment
     * @return {@link Boolean}
     */
    Boolean isAppointmentAvailable(Integer id, LocalDateTime dateTime);

    /**
     * <p>
     * This method is used to reschedule appointment and
     * getting details such as clinic id, doctor id, patient id, etc
     * for reschedule appointment into the database.
     * </p>
     *
     * @param appointmentDto {@link AppointmentDto} is appointment detail
     * @return {@link String}
     */
    String rescheduleAppointment(AppointmentDto appointmentDto);

    /**
     * <p>
     * This method is used to cancel the appointment
     * if you fixed appointment for visit doctor, you can
     * cancel the appointment by getting appointment id.
     * </p>
     *
     * @param id {@link Integer} is an id for unknown
     * @return {@link String}
     */

    String removeAppointmentById(Integer id);

    /**
     * <p>
     * This method is used to get appointments from the database
     * by patient Id using pagination which can get only the
     * required number of rows.
     * </p>
     *
     * @param pageNumber {@link Integer} is Page number
     * @param totalRows  {@link Integer} is number of rows required
     * @return {@link List<AppointmentDto>}
     */
    List<AppointmentDto> getAppointmentsByPatientId(Integer patientId, Integer pageNumber,
                                                    Integer totalRows);

    /**
     * <p>
     * This method is used to get appointments from the database
     * by doctor Id using pagination which can get only the
     * required number of rows.
     * </p>
     *
     * @param pageNumber {@link Integer} is Page number
     * @param totalRows  {@link Integer} is number of rows required
     * @return {@link List<AppointmentDto>}
     */
    List<AppointmentDto> getAppointmentsByDoctorId(Integer doctorId, Integer pageNumber,
                                                   Integer totalRows);

    /**
     * <p>
     * This method is used to get the count of appointments
     * of a doctor,patient booked appointment for visiting doctor,
     * we can get the count of appointment of a doctor by patient id
     * </p>
     *
     * @param patientId {@link Integer} is id of patient
     * @return {@link Integer}
     */
    Integer getCountOfAppointmentByPatientId(Integer patientId);

    /**
     * <p>
     * This method is used to get the count of appointments,
     * how many appointments are booked to the corresponding
     * doctor, this method will return the count of appointment
     * of a doctor by doctor id
     * </p>
     *
     * @param doctorId {@link Integer} is id of doctor
     * @return {@link List<AppointmentDto>}
     */
    Integer getCountOfAppointmentByDoctorId(Integer doctorId);
}

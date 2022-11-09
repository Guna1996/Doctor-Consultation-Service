/**
 * <p>
 * This package contains interfaces of Doctor clinic service,
 * Patient service, Doctor service, ClinicService,
 * AppointmentService, FeedbackService, SpecializationService,
 * TimeslotControllerService, VitalServiceImpl.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.TimeslotDto;

import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 * TimeslotService interface consists of abstract methods which is used
 * for performing CRUD operation. it is used to transfer objects between
 * controller and repository
 * </p>
 *
 * @author Bala Ashwanth
 * @since 2022-10-10
 */
public interface TimeslotService {

    /**
     * <p>
     * This method is used to add an timeslot
     * for a doctor by getting details such as
     * time format, time slot to allocate a doctor in a
     * particular clinic.
     * </p>
     *
     * @param timeslotDto {@link TimeslotDto} contains timeslots
     * @return {@link String}
     */
    String addTimeslot(TimeslotDto timeslotDto);

    /**
     * <p>
     * This method is used to get timeslots
     * from the database using pagination which can get
     * only the required number of rows.
     * </p>
     *
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is required number of rows to be displayed
     * @return {@link TimeslotDto}
     */
    List<TimeslotDto> getTimeslots(Integer pageNumber, Integer totalRows);

    /**
     * <p>
     * This method is used to get the count of timeslots
     * which is present in the active status in the database
     * and it will return the count of timeslots as an integer.
     * </p>
     *
     * @return {@link Integer}
     */
    Integer getTimeslotsCount();

    /**
     * <p>
     * This method is used to check whether the timeslot is present
     * in the list of default timeslots which is in the timeslot master table
     * otherwise it will given error message.
     * </p>
     *
     * @param localTime {@link LocalTime} is timeslot
     * @return {@link boolean}
     */
    boolean isValidTimeslot(LocalTime localTime, String timeFormat);
}

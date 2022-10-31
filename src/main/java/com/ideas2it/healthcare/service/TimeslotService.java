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

import com.ideas2it.healthcare.dto.TimeslotDto;

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
     * This abstract method is used to add timeslot
     * to the database
     * </p>
     *
     * @param timeslotDto {@link TimeslotDto} contains timeslots
     * @return {@link TimeslotDto}
     */
    TimeslotDto addTimeslot(TimeslotDto timeslotDto);

    /**
     * <p>
     * This abstract method is used to get timeslots
     * from the database
     * </p>
     *
     * @param pageNumber {@link Integer} is page number
     * @param totalRows {@link Integer} is required number of rows to be displayed
     * @return {@link TimeslotDto}
     */
    List<TimeslotDto> getTimeslots(Integer pageNumber, Integer totalRows);
}

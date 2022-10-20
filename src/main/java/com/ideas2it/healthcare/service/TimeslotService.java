/**
 * <p>
 * This is the base package for all the service interfaces
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.TimeslotDto;

import java.util.List;

/**
 * <p>
 * This Timeslot interface is a service interface and this
 * interface is used to contain the body of TimeslotServiceImpl
 * class's methods
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
     *
     * </p>
     *
     * @param timeslotDto {@link TimeslotDto}
     * @return {@link TimeslotDto}
     */
    TimeslotDto addTimeslot(TimeslotDto timeslotDto);

    /**
     * <p>
     * This abstract method is used to update timeslot
     * to the database
     *
     * </p>
     *
     * @param timeslotDto {@link TimeslotDto}
     * @return {@link TimeslotDto}
     */
    TimeslotDto updateTimeslot(TimeslotDto timeslotDto);

    /**
     * <p>
     * This abstract method is used to get timeslot
     * from the database
     *
     * </p>
     *
     * @param id {@link int}
     * @return {@link TimeslotDto}
     */
    TimeslotDto getTimeslotById(int id);

    /**
     * <p>
     * This abstract method is used to get timeslots
     * from the database
     *
     * </p>
     *
     * @param pageNumber {@link int}
     * @param totalRows  {@link int}
     * @return {@link TimeslotDto}
     */
    List<TimeslotDto> getTimeslots(int pageNumber, int totalRows);

    /**
     * <p>
     * This abstract method is used to delete timeslot
     * from the database
     *
     * </p>
     *
     * @param id {@link int}
     * @return {@link String}
     */
    String deleteTimeslot(int id);

}

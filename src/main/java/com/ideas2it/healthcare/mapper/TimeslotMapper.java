/**
 * <p>
 * This is the base package for all the mapper classes
 * which is for Doctor mapper, Patient mapper and Clinic mapper
 * classes
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.mapper;

import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.model.Timeslot;

/**
 * <p>
 * Timeslot mapper class is used convert Timeslot object into
 * Timeslot dto and also used to convert Timeslot dto into
 * Timeslot model
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
public class TimeslotMapper {

    /**
     * <p>
     * This method is used to convert Timeslot dto into
     * Timeslot model
     * </p>
     *
     * @param timeslotDto {@link TimeslotDto} contains timeslot details
     * @return {@link Timeslot}
     */
    public static Timeslot fromDto(TimeslotDto timeslotDto) {
        Timeslot timeslot = new Timeslot();
        if (null != timeslotDto) {
            timeslot.setId(timeslotDto.getId());
            timeslot.setTimeslot(timeslotDto.getTimeslot());
        }
        return timeslot;
    }

    /**
     * <p>
     * This method is used to convert Timeslot model into
     * Timeslot dto
     * </p>
     *
     * @param timeslot {@link Timeslot} contains timeslot details
     * @return {@link TimeslotDto}
     */
    public static TimeslotDto toDto(Timeslot timeslot) {
        TimeslotDto timeslotDto = new TimeslotDto();
        if (null != timeslot) {
            timeslotDto.setId(timeslot.getId());
            timeslotDto.setTimeslot(timeslot.getTimeslot());
        }
        return timeslotDto;
    }
}

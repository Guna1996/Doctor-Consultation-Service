/**
 * <p>
 * This is the base package for all the dto classes and
 * the dto package classes are Doctor dto,Specialization dto,
 * Clinic dto,Doctor clinic dto,Appointment dto,Feedback dto,Patient dto,
 * Patient vital dto,Timeslot dto.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import com.ideas2it.healthcare.common.ErrorConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * <p>
 * This is Timeslot Dto class for Timeslot
 * Dto main purpose is to transfer data  from an
 * entity object to Dto object, inorder to not show the
 * exact data to the user
 * </p>
 *
 * @author Bala Ashwanth N
 * @since 2022-10-10
 */
@Data
public class TimeslotDto {

    private int id;

    @NotNull(message = ErrorConstants.TIMESLOT_SHOULD_NOT_BE_EMPTY)
    private LocalTime timeslot;

    @NotNull(message = ErrorConstants.TIME_FORMAT_SHOULD_NOT_BE_EMPTY)
    private String timeFormat;
}

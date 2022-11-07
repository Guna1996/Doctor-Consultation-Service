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

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * This Specialization dto class is a Dto class and this class is used
 * to transfer the object from controller layer to service
 * layer.
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@Data
public class SpecializationDto {

    private int id;

    @NotNull(message = ErrorConstants.NAME_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.NAME_REGEX, message = ErrorConstants.NAME_FORMAT)
    private String name;

    private String status = Constants.ACTIVE;
}

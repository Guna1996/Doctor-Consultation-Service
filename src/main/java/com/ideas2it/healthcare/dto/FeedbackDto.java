/**
 * <p>
 * This is the base package for all the dto classes and
 * the dto package classes are DoctorDto,SpecializationDto,
 * ClinicDto,DoctorClinicDto,AppointmentDto,FeedbackDto,PatientDto,
 * VitalDto,TimeslotDto.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * This is a Dto class for Feedback
 * Dto main purpose is to transfer data from from an
 * entity object to Dto object, inorder to not show the
 * exact data to the user
 * </p>
 *
 * @author Bala Ashwanth N
 * @since 2022-10-10
 */
@Getter
@Setter
public class FeedbackDto {

    private int id;

    @NotNull(message = ErrorConstants.COMMENT_SHOULD_NOT_BE_NULL)
    private String comment;

    @NotNull(message = ErrorConstants.RATING_SHOULD_NOT_BE_NULL)
    @Min(value = 0, message = ErrorConstants.RATING_SHOULD_NOT_BE_NEGATIVE)
    private float rating;

    private DoctorDto doctor;

    private String status = Constants.ACTIVE;

    private PatientDto patient;

    private Integer total_entries;
}

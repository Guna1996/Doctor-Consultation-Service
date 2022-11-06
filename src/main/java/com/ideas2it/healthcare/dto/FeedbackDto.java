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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * This is a Dto class for Feedback
 * dto main purpose is to transfer data from from an
 * entity object to Dto object, inorder to not show the
 * exact data to the user
 * </p>
 *
 * @author Bala Ashwanth N
 * @since 2022-10-10
 */
@Data
public class  FeedbackDto {

    private int id;

    @NotNull(message = ErrorConstants.COMMENT_SHOULD_NOT_BE_NULL)
    private String comment;

    @NotNull(message = ErrorConstants.RATING_SHOULD_NOT_BE_NULL)
    @Min(value = 0, message = ErrorConstants.RATING_SHOULD_NOT_BE_NEGATIVE)
    private float rating;

    @NotNull(message = ErrorConstants.DOCTOR_SHOULD_NOT_BE_NULL)
    private DoctorDto doctor;

    private String status = Constants.ACTIVE;

    @NotNull(message = ErrorConstants.PATIENT_SHOULD_NOT_BE_NULL)
    private PatientDto patient;
}

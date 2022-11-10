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
import java.time.LocalDateTime;

/**
 * <p>
 * This is a Patient vital dto class for
 * Dto main purpose is to transfer data from from an
 * entity object to Dto object, inorder to not show the
 * exact data to the user
 * </p>
 *
 * @author Bala Ashwanth N
 * @since 2022-10-10
 */
@Data
public class PatientVitalDto {

    private int id;

    @NotNull(message = ErrorConstants.HEIGHT_SHOULD_NOT_BE_NULL)
    @Min(value = 1, message = ErrorConstants.HEIGHT_SHOULD_NOT_BE_NEGATIVE)
    private float height;

    @NotNull(message = ErrorConstants.WEIGHT_SHOULD_NOT_BE_NULL)
    @Min(value = 1, message = ErrorConstants.WEIGHT_SHOULD_NOT_BE_NEGATIVE)
    private float weight;

    @NotNull(message = ErrorConstants.PULSE_SHOULD_NOT_BE_NULL)
    @Min(value = 1, message = ErrorConstants.PULSE_SHOULD_NOT_BE_NEGATIVE)
    private float pulse;

    @NotNull(message = ErrorConstants.DIASTOLIC_SHOULD_NOT_BE_NULL)
    @Min(value = 1, message = ErrorConstants.DIASTOLIC_SHOULD_NOT_BE_NEGATIVE)
    private float diastolic;

    @NotNull(message = ErrorConstants.SYSTOLIC_SHOULD_NOT_BE_NULL)
    @Min(value = 1, message = ErrorConstants.SYSTOLIC_SHOULD_NOT_BE_NEGATIVE)
    private float systolic;

    @NotNull(message = ErrorConstants.SUGAR_LEVEL_SHOULD_NOT_BE_NULL)
    @Min(value = 1, message = ErrorConstants.SUGAR_LEVEL_SHOULD_NOT_BE_NEGATIVE)
    private float sugarLevel;

    @NotNull(message = ErrorConstants.PATIENT_SHOULD_NOT_BE_NULL)
    private PatientDto patient;

    private String status = Constants.ACTIVE;

    @NotNull(message = ErrorConstants.DOCTOR_SHOULD_NOT_BE_NULL)
    private DoctorDto doctor;

    private String bpRiskLevel;

    private LocalDateTime createdAt;

    @NotNull(message = ErrorConstants.APPOINTMENT_SHOULD_NOT_BE_EMPTY)
    private AppointmentDto appointment;

    private LocalDateTime updatedAt;
}

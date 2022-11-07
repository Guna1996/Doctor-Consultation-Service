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
import java.time.LocalDateTime;

/**
 * <p>
 * This Appointment dto class contains details for doctors appointment with patient
 * validates appointment details
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Data
public class AppointmentDto {

    private int id;

    @NotNull(message = ErrorConstants.SCHEDULED_ON_SHOULD_NOT_BE_NULL)
    private LocalDateTime scheduledOn;

    @NotNull(message = ErrorConstants.TIME_FORMAT_SHOULD_NOT_BE_EMPTY)
    @Pattern(regexp = Constants.TIME_FORMAT_REGEX, message = ErrorConstants.ENTER_AM_OR_PM)
    private String timeFormat;

    private LocalDateTime createdAt;

    @NotNull(message = ErrorConstants.PATIENT_SHOULD_NOT_BE_NULL)
    private PatientDto patient;

    @NotNull(message = ErrorConstants.DOCTOR_SHOULD_NOT_BE_NULL)
    private DoctorDto doctor;

    @NotNull(message = ErrorConstants.CLINIC_SHOULD_NOT_BE_NULL)
    private ClinicDto clinic;

    private String status = Constants.ACTIVE;
}

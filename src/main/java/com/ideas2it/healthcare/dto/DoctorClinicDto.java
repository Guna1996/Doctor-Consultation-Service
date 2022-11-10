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
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * This Doctor clinic dto  mainly used for reducing the number of expensive remote calls.
 * In order to convert data between the DTO and any entity objects,
 * the assembler object was defined, but now we are using mappers for converting data.
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@Data
public class DoctorClinicDto {

    private int id;

    private String status = Constants.ACTIVE;

    @NotNull(message = ErrorConstants.DOCTOR_SHOULD_NOT_BE_NULL)
    private DoctorDto doctor;

    @NotNull(message = ErrorConstants.CLINIC_SHOULD_NOT_BE_NULL)
    private ClinicDto clinic;

    @NotNull(message = ErrorConstants.TIMESLOT_SHOULD_NOT_BE_NULL)
    private List<TimeslotDto> timeslots;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

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
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * This DoctorClinicDto  mainly used for reducing the number of expensive remote calls.
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

    @NotNull(message = "doctor shouldn't be null")
    private DoctorDto doctor;

    @NotNull(message = "clinic shouldn't be null")
    private ClinicDto clinic;

    private List<TimeslotDto> timeslots;
}

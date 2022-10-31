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
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * <p>
 * This is a Dto class for Vital
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
public class VitalsDto {

    private int id;

    @Min(value = 0, message = "Height shouldn't be negative")
    private float height;


    private float weight;

    private float pulse;

    private float diastolic;

    private float systolic;

    private float sugarLevel;

    private PatientDto patient;

    private String status = Constants.ACTIVE;

    private DoctorDto doctor;

    private String BPRiskLevel;

    private LocalDateTime createdAt;
}

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
import com.ideas2it.healthcare.util.VitalUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = ErrorConstants.HEIGHT_SHOULD_NOT_BE_NULL)
    @Min(value = 0, message = ErrorConstants.HEIGHT_SHOULD_NOT_BE_NEGATIVE)
    private float height;

    @NotNull(message = ErrorConstants.WEIGHT_SHOULD_NOT_BE_NULL)
    @Min(value = 0, message = ErrorConstants.WEIGHT_SHOULD_NOT_BE_NEGATIVE)
    private float weight;

    @NotNull(message = ErrorConstants.PULSE_SHOULD_NOT_BE_NULL)
    @Min(value =0, message = ErrorConstants.PULSE_SHOULD_NOT_BE_NEGATIVE)
    private float pulse;

    @NotNull(message = ErrorConstants.DIASTOLIC_SHOULD_NOT_BE_NULL)
    @Min(value =0, message = ErrorConstants.DIASTOLIC_SHOULD_NOT_BE_NEGATIVE)
    private float diastolic;

    @NotNull(message = ErrorConstants.SYSTOLIC_SHOULD_NOT_BE_NULL)
    @Min(value =0, message = ErrorConstants.SYSTOLIC_SHOULD_NOT_BE_NEGATIVE)
    private float systolic;

    @NotNull(message = ErrorConstants.SUGAR_LEVEL_SHOULD_NOT_BE_NULL)
    @Min(value =0, message = ErrorConstants.SUGAR_LEVEL_SHOULD_NOT_BE_NEGATIVE)
    private float sugarLevel;

    private PatientDto patient;

    private String status = Constants.ACTIVE;

    private DoctorDto doctor;

    @Getter(AccessLevel.NONE)
    private String bpRiskLevel;

    private LocalDateTime createdAt;

    private Integer total_entries;

    public String getBpRiskLevel() {
        bpRiskLevel = VitalUtil.getBPRiskLevel(getSystolic(), getDiastolic());
        return bpRiskLevel;
    }
}

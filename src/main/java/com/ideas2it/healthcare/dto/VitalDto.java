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

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Calendar;

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
public class VitalDto {

    private int id;

    private float height;

    private float weight;

    private float pulse;

    private float diastolic;

    private float systolic;

    private float sugarLevel;

    private PatientDto patient;

    private String status;

    private DoctorDto doctor;

    private String bloodPressure;

    private LocalDateTime createdAt;
}

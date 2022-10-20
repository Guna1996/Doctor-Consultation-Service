/**
 * <p>
 * This is the base package for all the dto classes and
 * the dto package classes are DoctorDto,SpecializationDto,
 * ClinicDto,DoctorClinicDto,AppointmentDto,FeedbackDto,PatientDto,
 * VitalDto,TimeslotDto.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *This is a Dto class for Feedback
 * Dto main purpose is to transfer data from from an
 * entity object to Dto object, inorder to not show the
 * exact data to the user
 * </p>
 *
 * @author  Bala Ashwanth N
 *
 * @since   2022-10-10
 */
@Getter
@Setter
public class FeedbackDto {

    private int id;

    private String comment;

    private float rating;

    private DoctorDto doctor;

    private String status;

    private PatientDto patient;
}

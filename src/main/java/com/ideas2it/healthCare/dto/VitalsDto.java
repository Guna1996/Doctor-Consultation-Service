/**
 * <p>
 * This is the base package for all the dto classes and
 * the dto package classes are DoctorDto,SpecializationDto,
 * ClinicDto,DoctorClinicDto,AppointmentDto,FeedbackDto,PatientDto,
 * VitalsDto,TimeslotDto.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */

package com.ideas2it.healthCare.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;

/**
 * <p>
 *This is a Dto class for Vitals
 * Dto's is main purpose is to transfer data from from an
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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VitalsDto {

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
}

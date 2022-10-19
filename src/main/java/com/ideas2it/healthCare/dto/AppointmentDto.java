/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctorDto, clinicDto and patientDto
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * This AppointmentDto class contains details for doctors appointment with patient
 * validates appointment details
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AppointmentDto {

    private int id;

    private LocalDateTime scheduledOn;

    private PatientDto patient;

    private DoctorDto doctor;

    private ClinicDto clinic;

    private String status;
}

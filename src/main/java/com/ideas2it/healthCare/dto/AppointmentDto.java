/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctorDto, clinicDto and patientDto
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.dto;

import lombok.Builder;
import lombok.Data;

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
@Data
public class AppointmentDto {

    private int id;

    private LocalDateTime scheduledOn;

    private int patientId;

    private int doctorId;

    private int clinicId;

    private PatientDto patient;

    private DoctorDto doctor;

    private ClinicDto clinic;

    private String status;

    private LocalDate scheduledDate;

    private LocalTime scheduledTime;
}

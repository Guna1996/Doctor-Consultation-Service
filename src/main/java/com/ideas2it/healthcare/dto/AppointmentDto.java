/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctorDto, clinicDto and patientDto
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import com.ideas2it.healthcare.common.Constants;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
public class AppointmentDto {

    private int id;

    private LocalDateTime scheduledOn;

    private LocalDateTime createdAt;

    private PatientDto patient;

    private DoctorDto doctor;

    private ClinicDto clinic;

    private String status = Constants.ACTIVE;
}

/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctorDto, clinicDto and patientDto
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
@Data
public class AppointmentDto {

    private int id;

    private LocalDateTime scheduledOn;

    private LocalDateTime createdAt;

    @NotNull(message = ErrorConstants.PATIENT_SHOULD_NOT_BE_NULL)
    private PatientDto patient;

    @NotNull(message = ErrorConstants.DOCTOR_SHOULD_NOT_BE_NULL)
    private DoctorDto doctor;

    @NotNull(message = ErrorConstants.CLINIC_SHOULD_NOT_BE_NULL)
    private ClinicDto clinic;

    private String status = Constants.ACTIVE;
}

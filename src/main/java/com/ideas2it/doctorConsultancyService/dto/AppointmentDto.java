/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctorDto, clinicDto and patientDto
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.dto;

import com.ideas2it.doctorConsultancyService.model.Clinic;
import com.ideas2it.doctorConsultancyService.model.Doctor;
import com.ideas2it.doctorConsultancyService.model.Patient;
import lombok.Builder;
import lombok.Data;

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
@Builder
public class AppointmentDto {

    private int id;

    private LocalDateTime scheduledOn;

    private PatientDto patientDto;

    private DoctorDto doctorDto;

    private ClinicDto clinicDto;
}

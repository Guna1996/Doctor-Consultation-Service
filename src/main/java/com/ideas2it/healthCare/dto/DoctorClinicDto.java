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

import com.ideas2it.healthCare.model.Clinic;
import com.ideas2it.healthCare.model.Doctor;
import com.ideas2it.healthCare.model.Timeslot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * This DoctorClinicDto  mainly used for reducing the number of expensive remote calls.
 * In order to convert data between the DTO and any entity objects,
 * the assembler object was defined, but now we are using mappers for converting data.
 * </p>
 *
 * @author  Ramachandran
 *
 * @version 1
 *
 * @since   2022-10-10
 */
@Data
public class DoctorClinicDto {

    private int id;
    private int doctorId;
    private int clinicId;
    private int timeSlotId;
    private String status;
    private DoctorDto doctor;
    private ClinicDto clinic;
    private TimeslotDto timeslot;
}

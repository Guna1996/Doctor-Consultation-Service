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
package com.ideas2it.doctorConsultancyService.dto;

import com.ideas2it.doctorConsultancyService.model.Clinic;
import com.ideas2it.doctorConsultancyService.model.Doctor;
import com.ideas2it.doctorConsultancyService.model.Timeslot;

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
public class DoctorClinicDto {

    private int id;
    private Doctor doctor;
    private Clinic clinic;
    private Timeslot timeslot;
}

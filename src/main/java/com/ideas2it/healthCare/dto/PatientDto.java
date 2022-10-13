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

import com.ideas2it.healthCare.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * The PatientDto is mainly used for reducing the number of expensive remote calls.
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

    private int id;

    //@Pattern(regexp = Constants.NAME_REGEX, message = "Please Enter Valid Name")
    private String name;

    //@Pattern(regexp = Constants.DATE_REGEX, message = "Please Enter Valid Date Of Birth")
    private LocalDate dateOfBirth;

    //@Pattern(regexp = Constants.GENDER_REGEX, message = "Please Enter Valid Gender")
    private String gender;

    //@Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = "Please Enter Valid Mobile Number")
    private String mobileNumber;

    //@Pattern(regexp = Constants.EMAIL_REGEX, message = "Please Enter Valid Email")
    private String email;

    private List<FeedbackDto> feedback;

    private List<AppointmentDto> appointment;

    private List<VitalsDto> vitals;
}

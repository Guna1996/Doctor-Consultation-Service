/**
 * <p>
 * This is the base package for all the dto classes and
 * the dto package classes are DoctorDto,SpecializationDto,
 * ClinicDto,DoctorClinicDto,AppointmentDto,FeedbackDto,PatientDto,
 * VitalDto,TimeslotDto.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import com.ideas2it.healthcare.common.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * <p>
 * The PatientDto is mainly used for reducing the number of expensive remote calls.
 * In order to convert data between the DTO and any entity objects,
 * the assembler object was defined, but now we are using mappers for converting data.
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@Getter
@Setter
public class PatientDto {

    private int id;

    @NotNull(message = "Name is mandatory")
    @Pattern(regexp = Constants.NAME_REGEX, message = "Enter You Name in this (FirstName SecondName) format")
    private String name;

    @NotNull(message = "Registration year is mandatory")
    @Past(message = "Entered Year is not valid")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is mandatory")
    @Pattern(regexp = Constants.GENDER_REGEX, message = "Enter Male or Female")
    private String gender;

    @NotNull(message = "Mobile Number is mandatory")
    @Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = "Enter valid Mobile Number")
    private String mobileNumber;

    @Email(message = "Enter valid mail id")
    private String email;

    private String status = Constants.ACTIVE;
}

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
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
@Getter
@Setter
public class PatientDto {

    private int id;

    @NotNull(message = ErrorConstants.NAME_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.NAME_REGEX, message = MessageConstants.NAME_FORMAT)
    private String name;

    @NotNull(message = ErrorConstants.DATE_OF_BIRTH_SHOULD_NOT_BE_NULL)
    @Past(message = MessageConstants.ENTER_VALID_DATE_OF_BIRTH)
    private LocalDate dateOfBirth;

    @NotNull(message = ErrorConstants.GENDER_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.GENDER_REGEX, message = MessageConstants.ENTER_MALE_OR_FEMALE)
    private String gender;

    @NotNull(message = ErrorConstants.MOBILE_NUMBER_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = MessageConstants.ENTER_VALID_MOBILE_NUMBER)
    private String mobileNumber;

    @Email(message = MessageConstants.ENTER_VALID_EMAIL)
    private String email;

    private String status = Constants.ACTIVE;
}

/**
 * <p>
 * This is the base package for all the dto classes and
 * the dto package classes are Doctor dto,Specialization dto,
 * Clinic dto,Doctor clinic dto,Appointment dto,Feedback dto,Patient dto,
 * Patient vital dto,Timeslot dto.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * This Doctor dto class is a Dto class and this class is used
 * to transfer the object from controller layer to service
 * layer.
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@Data
public class DoctorDto {

    private int id;

    @NotNull(message = ErrorConstants.NAME_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.NAME_REGEX, message = ErrorConstants.NAME_FORMAT)
    private String name;

    @NotNull(message = ErrorConstants.DATE_OF_BIRTH_SHOULD_NOT_BE_NULL)
    @Past(message = ErrorConstants.ENTER_VALID_DATE_OF_BIRTH)
    private LocalDate dateOfBirth;

    private int age;

    private int experience;

    @NotNull(message = ErrorConstants.GENDER_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.GENDER_REGEX, message = ErrorConstants.ENTER_MALE_OR_FEMALE)
    private String gender;

    @NotNull(message = ErrorConstants.QUALIFICATION_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.QUALIFICATION_REGEX, message = ErrorConstants.ENTER_VALID_QUALIFICATION)
    private String qualification;

    private Set<SpecializationDto> specializations;

    @NotNull(message = ErrorConstants.REGISTRATION_YEAR_SHOULD_NOT_BE_NULL)
    @PastOrPresent(message = ErrorConstants.ENTER_VALID_REGISTRATION_YEAR)
    private LocalDate dateOfRegistration;

    @NotNull(message = ErrorConstants.EMAIL_SHOULD_NOT_BE_NULL)
    @Email(message = ErrorConstants.ENTER_VALID_EMAIL)
    private String email;

    @NotNull(message = ErrorConstants.MOBILE_NUMBER_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = ErrorConstants.ENTER_VALID_MOBILE_NUMBER)
    private String mobileNumber;

    @NotNull(message = ErrorConstants.CITY_NAME_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.COUNTRY_REGEX, message = ErrorConstants.ENTER_VALID_CITY_NAME)
    private String city;

    private String status = Constants.ACTIVE;

    private int consultationFee;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

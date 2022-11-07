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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * This clinicDto class contains details of clinic
 * validates clinic details
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Data
public class ClinicDto {

    private int id;

    @NotNull(message = ErrorConstants.NAME_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.NAME_REGEX, message = ErrorConstants.NAME_FORMAT)
    private String name;

    @Min(value = 1, message = ErrorConstants.DOOR_NUMBER_CANNOT_BE_NEGATIVE_OR_NULL)
    @NotNull(message = ErrorConstants.DOOR_NUMBER_SHOULD_NOT_BE_NULL)
    private int doorNumber;

    @NotNull(message = ErrorConstants.STREET_NAME_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.NAME_REGEX, message = ErrorConstants.ENTER_VALID_STREET_NAME)
    private String streetName;

    @NotNull(message = ErrorConstants.CITY_NAME_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.COUNTRY_REGEX, message = ErrorConstants.ENTER_VALID_CITY_NAME)
    private String city;

    @NotNull(message = ErrorConstants.STATE_SHOULD_NOT_NE_NULL)
    @Pattern(regexp = Constants.COUNTRY_REGEX, message = ErrorConstants.ENTER_VALID_STATE_NAME)
    private String state;

    @Min(value = 1, message = ErrorConstants.PIN_CODE_CANNOT_BE_NEGATIVE_OR_ZERO)
    @NotNull(message = ErrorConstants.PIN_CODE_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.PIN_CODE_REGEX, message = ErrorConstants.ENTER_VALID_PIN_CODE)
    private String pinCode;

    @Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, 
            message = ErrorConstants.MOBILE_NUMBER_MUST_CONTAIN_TEN_NUMBERS_ONLY)
    @NotNull(message = ErrorConstants.MOBILE_NUMBER_SHOULD_NOT_BE_NULL)
    private String contactNumber;

    private String status = Constants.ACTIVE;
}

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
import com.ideas2it.healthcare.common.MessageConstants;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class ClinicDto {

    private int id;

    @NotNull(message = ErrorConstants.NAME_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.NAME_REGEX, message = MessageConstants.NAME_FORMAT)
    private String name;

    @Min(value = 1, message = ErrorConstants.DOOR_NUMBER_CANNOT_BE_NEGATIVE_OR_NULL)
    @NotNull(message = ErrorConstants.DOOR_NUMBER_SHOULD_NOT_BE_NULL)
    private int doorNumber;

    @NotNull(message = ErrorConstants.STREET_NAME_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.COUNTRY_REGEX, message = MessageConstants.ENTER_VALID_STREET_NAME)
    private String streetName;

    @NotNull(message = ErrorConstants.CITY_NAME_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.COUNTRY_REGEX, message = MessageConstants.ENTER_VALID_CITY_NAME)
    private String city;

    @NotNull(message = ErrorConstants.STATE_SHOULD_NOT_NE_NULL)
    @Pattern(regexp = Constants.COUNTRY_REGEX, message = MessageConstants.ENTER_VALID_STATE_NAME)
    private String state;

    @Min(value = 1, message = ErrorConstants.PIN_CODE_CANNOT_BE_NEGATIVE_OR_ZERO)
    @NotNull(message = ErrorConstants.PIN_CODE_SHOULD_NOT_BE_NULL)
    @Pattern(regexp = Constants.PIN_CODE_REGEX, message = MessageConstants.ENTER_VALID_PIN_CODE)
    private String pinCode;

    @Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = ErrorConstants.INPUT_CONTAINS_TEN_NUMBERS_ONLY)
    @NotNull(message = MessageConstants.MOBILE_NUMBER_SHOULD_NOT_BE_NULL)
    private String contactNumber;

    private String status = Constants.ACTIVE;
}

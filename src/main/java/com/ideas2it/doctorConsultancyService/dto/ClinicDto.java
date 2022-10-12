/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctorDto, clinicDto and patientDto
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.dto;

import com.ideas2it.doctorConsultancyService.common.Constants;
import com.ideas2it.doctorConsultancyService.model.Appointment;
import com.ideas2it.doctorConsultancyService.model.DoctorClinic;
import com.ideas2it.doctorConsultancyService.model.Vitals;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * <p>
 * This clinicDto class contains details of clinic
 * vaidates clinic details
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Data
@Builder
public class ClinicDto {

    private int id;

    @NotNull(message = "Name shouldn't be null")
    private String name;

    @Min(value = 1, message = "Door number cannot be negative or zero")
    @NotNull(message = "door number shouldn't be null")
    @Pattern(regexp = Constants.STREET_REGEX, message = "Please, Enter valid door number")
    private int doorNumber;

    @NotNull(message = "Street name shouldn't be null")
    @Pattern(regexp = Constants.STREET_REGEX, message = "Please, Enter valid street name")
    private String streetName;

    @NotNull(message = "city shouldn't be null")
    @Pattern(regexp = Constants.COUNTRY_REGEX, message = "Please, Enter valid city name")
    private String city;

    @NotNull(message = "state shouldn't be null")
    @Pattern(regexp = Constants.COUNTRY_REGEX, message = "Please, Enter valid state name")
    private String state;

    @Min(value = 1, message = "pin code cannot be negative or zero")
    @NotNull(message = "Pin code shouldn't be null")
    @Pattern(regexp = Constants.PINCODE_REGEX, message = "Please, Enter valid pin code")
    private int pinCode;

    @Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = "Type only 10 numbers")
    @NotNull(message = "Mobile number shouldn't be null")
    private String contactNumber;
}

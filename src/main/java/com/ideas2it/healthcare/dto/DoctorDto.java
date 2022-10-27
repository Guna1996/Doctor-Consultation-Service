/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.dto;

import com.ideas2it.healthcare.common.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * This DoctorDto class is a Dto class and this class is used
 * to transfer the object from controller layer to service
 * layer
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@Getter
@Setter
public class DoctorDto {

    private int id;

    @NotNull(message = "Name is mandatory")
    @Pattern(regexp = Constants.NAME_REGEX, message = "Enter Your Name in this (FirstName SecondName) format")
    private String name;

    @NotNull(message = "Date Of Birth is mandatory")
    @Past(message = "Enter valid year")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is mandatory")
    @Pattern(regexp = Constants.GENDER_REGEX, message = "Enter Male or Female")
    private String gender;

    @NotNull(message = "Qualification is mandatory")
    @Pattern(regexp = Constants.QUALIFICATION_REGEX, message = "Don't enter numbers")
    private String qualification;

    private Set<SpecializationDto> specializations;

    @NotNull(message = "Registration year is mandatory")
    @PastOrPresent(message = "Entered Year is not valid")
    private LocalDate dateOfRegistration;

    @NotNull(message = "Mobile Number is mandatory")
    @Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = "Enter valid Mobile Number")
    private String mobileNumber;

    @NotNull(message = "City is mandatory")
    @Pattern(regexp = Constants.COUNTRY_REGEX, message = "Enter valid City name")
    private String city;

    private String status = Constants.ACTIVE;

    private List<FeedbackDto> feedbacks;

    private List<AppointmentDto> appointments;

    private List<DoctorClinicDto> clinics;

    private int consultationFee;
}

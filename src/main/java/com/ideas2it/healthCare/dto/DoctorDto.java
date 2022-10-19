/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ideas2it.healthCare.model.DoctorClinic;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
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
 * @author  Mohamed Jubair
 *
 * @version 1
 *
 * @since   2022-10-10
 */
@Getter
@Setter
public class DoctorDto {

    private int id;

    @NotNull
    @Size(min = 2, message = "user name should have at least 2 characters")
    //@Pattern(regexp = Constants.NAME_REGEX, message = "Enter You Name in this (Firstname Secondname) format")
    private String name;

    @NotNull(message = "Date Of Birth is mandatory")
    //@Pattern(regexp = Constants.DATE_REGEX, message = "Enter Date of Birth in (YYYY-MM-DD) this format")
    @Past(message = "Enter valid year")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is mandatory")
    //@Pattern(regexp = Constants.GENDER_REGEX, message = "Enter Male or Female")
    private String gender;

    @NotNull(message = "Qualification is mandatory")
    //@Pattern(regexp = Constants.QUALIFICATION_REGEX, message ="Don't enter numbers")
    private String qualification;

    private Set<SpecializationDto> specializations;

    @NotNull(message = "Registration year is mandatory")
    //@Pattern(regexp = Constants.DATE_REGEX, message = "Enter Date of Birth in (YYYY-MM-DD) this format")
    //@PastOrPresent(message = "Entered Year is not valid")
    private LocalDate dateOfRegistration;

    @NotNull(message = "Mobile Number is mandatory")
    //@Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = "Enter valid Mobile Number")
    private String mobileNumber;

    //@NotNull(message = "City is mandatory")
    //@Pattern(regexp = Constants.COUNTRY_REGEX, message = "Enter valid City name")
    private String city;

    private String status;

    private List<FeedbackDto> feedbacks;

    private List<AppointmentDto> appointments;

    private List<DoctorClinicDto> clinics;
}

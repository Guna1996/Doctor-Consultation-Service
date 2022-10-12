/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.dto;

import com.ideas2it.doctorConsultancyService.common.Constants;
import com.ideas2it.doctorConsultancyService.model.*;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

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
@Data
public class DoctorDto {

    private int id;

    @NotNull
    @Size(min = 2, message = "user name should have at least 2 characters")
    @Pattern(regexp = Constants.NAME_REGEX, message = "Enter You Name in this (Firstname Secondname) format")
    private String name;

    @NotNull(message = "Date Of Birth is mandatory")
    @Pattern(regexp = Constants.DATE_REGEX, message = "Enter Date of Birth in (YYYY-MM-DD) this format")
    @Past(message = "Enter valid year")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is mandatory")
    private String gender;

    @NotNull(message = "Qualification is mandatory")
    @Pattern(regexp = Constants.QUALIFICATION_REGEX, message ="Don't enter numbers")
    private String qualification;

    private List<Specialization> specialization;

    @NotNull(message = "Registration year is mandatory")
    @Pattern(regexp = Constants.DATE_REGEX, message = "Enter Date of Birth in (YYYY-MM-DD) this format")
    @PastOrPresent(message = "Entered Year is not valid")
    private LocalDate registrationYear;

    @NotNull(message = "Mobile Number is mandatory")
    @Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = "Enter valid Mobile Number")
    private long mobileNumber;

    @NotNull(message = "City is mandatory")
    @Pattern(regexp = Constants.COUNTRY_REGEX, message = "Enter valid City name")
    private String city;

    private List<Feedback> feedbacks;

    private List<Appointment> appointments;

    private List<Vitals> vitals;

    private List<Timeslot> timeslots;
}

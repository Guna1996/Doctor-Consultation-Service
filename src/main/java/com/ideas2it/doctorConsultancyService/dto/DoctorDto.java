/**
 * <p>
 * This is the base package for all the Dto classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.dto;

import com.ideas2it.doctorConsultancyService.model.Appointment;
import com.ideas2it.doctorConsultancyService.model.FeedBack;
import com.ideas2it.doctorConsultancyService.model.Specialization;
import com.ideas2it.doctorConsultancyService.model.Vitals;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
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
    private String name;

    @NotNull(message = "Date Of Birth is mandatory")
    @Past
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is mandatory")
    private String gender;

    @NotNull(message = "Qualification is mandatory")
    private String qualification;

    private Specialization specialization;

    @NotNull(message = "Registration year is mandatory")
    @PastOrPresent
    private int registrationYear;

    @NotNull(message = "Mobile Number year is mandatory")
    private long mobileNumber;

    @NotNull(message = "City year is mandatory")
    private String city;

    private List<FeedBack> feedbacks;

    private List<Appointment> appointments;

    private List<Vitals> vitals;
}

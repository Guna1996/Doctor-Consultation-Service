/**
 * <p>
 * This is the base package for all the model classes and
 * the model package contains classes are Doctor,Specialization,
 * Clinic,DoctorClinic,Oppointment,Feedback,Patient,
 * Vitals,Timeslot.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;

/**
 * <p>
 * This Patient class contains getter and setter method internally
 * because we are using lombok dependency it internally invokes the
 * getter and setter method for retrieving patient details from the
 * database and also setter methods used for inserting details into
 * database.
 * </p>
 *
 * @author  Ramachandran
 *
 * @version 1
 *
 * @since   2022-10-10
 */
@Data
@Entity
@Table(name="patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    @Pattern(regexp = "[a-z]{3}",message = "Please enter valid name")
    private String name;

    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name="gender")
    @Pattern(regexp = "[a-z]",message = "Please enter valid gender")
    private String gender;

    @Column(name="mobile_number")
    @Pattern(regexp = "^\\d{10}$", message = "Please enter valid mobile number ")
    private long mobileNumber;

    @Column(name="email")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Please enter valid email")
    private String email;
}

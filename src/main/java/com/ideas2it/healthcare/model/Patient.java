/**
 * <p>
 * This is the base package for all the model classes and
 * the model package contains classes are Doctor,Specialization,
 * Clinic,DoctorClinic,Appointment,Feedback,Patient,
 * Vital,Timeslot.
 * </p>
 * <p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.model;

import com.ideas2it.healthcare.common.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Ramachandran
 *
 * @version 1
 *
 * @since 2022-10-10
 */
@Getter
@Setter
@Entity(name = Constants.PATIENT)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.ID)
    private int id;

    @Column(name = Constants.NAME)
    private String name;

    @Column(name = Constants.DATE_OF_BIRTH)
    private LocalDate dateOfBirth;

    @Column(name = Constants.GENDER)
    private String gender;

    @Column(name = Constants.MOBILE_NUMBER)
    private String mobileNumber;

    @Column(name = Constants.EMAIL)
    private String email;

    @Column(name = Constants.STATUS)
    private String status;
}

/**
 * <p>
 * This is the base package for all the model classes and
 * the model package contains classes are Doctor,Specialization,
 * Clinic,DoctorClinic,Appointment,Feedback,Patient,
 * Vitals,Timeslot.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDate;
import java.util.List;

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
@Entity
@Table(name = "patient")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "patient")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointment;

    @OneToMany(mappedBy = "patient")
    private List<Vitals> vitals;
}

/**
 * <p>
 * This is the base package for all the model classes and
 * the model package contains classes are Doctor,Specialization,
 * Clinic,DoctorClinic,Appointment,Feedback,Patient,
 * Vital,Timeslot.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */

package com.ideas2it.healthcare.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <p>
 * This Feedback class internally contains getter and setter
 * methods because of using lombok dependency
 * </p>
 *
 * @author  Bala Ashwanth.N
 *
 * @version 1
 *
 * @since   2022-10-10
 */
@Entity(name = "feedback")
@Getter
@Setter
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String comment;

    @Column
    private float rating;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}

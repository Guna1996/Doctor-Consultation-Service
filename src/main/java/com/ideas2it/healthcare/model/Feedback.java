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
import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 * This Feedback class internally contains getter and setter
 * methods because of using lombok dependency
 * </p>
 *
 * @author Bala Ashwanth.N
 *
 * @version 1
 *
 * @since 2022-10-10
 */
@Entity(name = Constants.FEEDBACK)
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = Constants.COMMENT)
    private String comment;

    @Column(name = Constants.RATING)
    private float rating;

    @OneToOne
    @JoinColumn(name = Constants.DOCTOR_ID)
    private Doctor doctor;

    @Column(name = Constants.STATUS)
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = Constants.PATIENT_ID)
    private Patient patient;
}

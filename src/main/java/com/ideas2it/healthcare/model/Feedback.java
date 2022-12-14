/**
 * <p>
 * This is the base package for all the model classes and
 * the model package contains classes are Doctor, Specialization,
 * Clinic, Doctor clinic, Appointment, Feedback, Patient,
 * Patient Vital,Timeslot.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.model;

import com.ideas2it.healthcare.common.Constants;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * <p>
 * This Feedback class internally contains getter and setter
 * methods for regarding fields because of using lombok dependency
 * </p>
 *
 * @author Bala Ashwanth.N
 * @version 1
 * @since 2022-10-10
 */
@Data
@Entity(name = Constants.FEEDBACK)
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

    @OneToOne
    @JoinColumn(name = Constants.APPOINTMENT, unique = true)
    private Appointment appointment;

    @CreationTimestamp
    @Column(name = Constants.CREATED_AT)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = Constants.UPDATED_AT)
    private LocalDateTime updatedAt;
}

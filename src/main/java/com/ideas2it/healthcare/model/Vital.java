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

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * <p>
 * This Vital class internally contains getter and setter
 * methods because of using lombok dependency
 * </p>
 *
 * @author Bala Ashwanth.N
 *
 * @since 2022-10-10
 */
@Getter
@Setter
@Entity(name = "vital")
public class Vital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private float height;

    @Column
    private float weight;

    @Column
    private float pulse;

    @Column
    private float diastolic;

    @Column
    private float systolic;

    @Column(name = "sugar_level")
    private float sugarLevel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column
    private String status;

    @ManyToOne(targetEntity = Doctor.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @Transient
    private String bloodPressure;
}

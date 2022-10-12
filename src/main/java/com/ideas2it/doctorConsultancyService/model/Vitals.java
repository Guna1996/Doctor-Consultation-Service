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

package com.ideas2it.doctorConsultancyService.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <p>
 * This Vitals class internally contains getter and setter
 * methods because of using lombok dependency
 * </p>
 *
 * @author  Bala Ashwanth.N
 *
 * @version 1
 *
 * @since   2022-10-10
 */

@Data
@Entity
public class Vitals {

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

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column
    private String status;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;
}

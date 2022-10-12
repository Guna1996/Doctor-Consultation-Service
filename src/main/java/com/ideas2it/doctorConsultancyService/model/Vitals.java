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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Vitals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float height;

    private float weight;

    private float pulse;

    private float diastolic;

    private float systolic;

    @Column(name = "sugar_level")
    private float sugarLevel;

    @Column(name = "patient_id")
    @ManyToOne
    private Patient patient;

    @JoinColumn(name = "doctor_id")
    @ManyToOne
    private Doctor doctor;
}

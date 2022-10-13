/**
 * <p>
 * This is the base package for all the model classes and
 * the model package contains classes are Doctor,Specialization,
 * Clinic,DoctorClinic,appointment,Feedback,Patient,
 * Vitals,Timeslot.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <p>
 * This DoctorClinic class internally contains getter and setter
 * methods because of using lombok dependency and by using this class,
 * clinic class can get the doctor availability.
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
@Table(name="doctor_clinic")
public class DoctorClinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="clinic_id")
    private Clinic clinic;

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="timeslot_id")
    private Timeslot timeslot;
}

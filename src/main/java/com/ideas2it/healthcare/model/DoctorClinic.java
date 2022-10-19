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
package com.ideas2it.healthcare.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

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
@Getter
@Setter
@Entity(name = "doctorClinic")
@Table(name="doctor_clinic")
public class DoctorClinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="status")
    private String status;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="clinic_id")
    private Clinic clinic;

    @ManyToMany
    @JoinTable(name = "doctor_clinic_timeslot",
            joinColumns = @JoinColumn(name = "doctor_clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "timeslot_id"))
    private List<Timeslot> timeslots;
}

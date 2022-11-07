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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * <p>
 * This Doctor clinic class internally contains getter and setter
 * methods for regarding fields because of using lombok dependency
 *
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@Data
@Entity(name = Constants.DOCTOR_CLINIC)
public class DoctorClinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.ID)
    private int id;

    @Column(name = Constants.STATUS)
    private String status;

    @OneToOne
    @JoinColumn(name = Constants.DOCTOR_ID)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = Constants.CLINIC_ID)
    private Clinic clinic;

    @OneToMany
    @JoinTable(name = Constants.DOCTOR_CLINIC_TIMESLOT,
            joinColumns = @JoinColumn(name = Constants.DOCTOR_CLINIC_ID),
            inverseJoinColumns = @JoinColumn(name = Constants.TIMESLOT_ID))
    private List<Timeslot> timeslots;
}

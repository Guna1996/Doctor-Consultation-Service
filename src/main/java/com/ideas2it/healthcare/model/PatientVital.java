/**
 * <p>
 * This is the base package for all the model classes and
 * the model package contains classes are Doctor,Specialization,
 * Clinic,Doctor clinic,Appointment,Feedback,Patient,
 * Patient vital,Timeslot.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.model;

import com.ideas2it.healthcare.common.Constants;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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
 * This Patient vital class internally contains getter and setter
 * methods for regarding fields because of using lombok dependency
 * </p>
 *
 * @author Bala Ashwanth.N
 * @since 2022-10-10
 */
@Data
@Entity(name = Constants.VITAL)
public class PatientVital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = Constants.HEIGHT)
    private float height;

    @Column(name = Constants.WEIGHT)
    private float weight;

    @Column(name = Constants.PULSE)
    private float pulse;

    @Column(name = Constants.DIASTOLIC)
    private float diastolic;

    @Column(name = Constants.SYSTOLIC)
    private float systolic;

    @Column(name = Constants.SUGAR_LEVEL)
    private float sugarLevel;

    @OneToOne
    @JoinColumn(name = Constants.PATIENT_ID)
    private Patient patient;

    @Column
    private String status = Constants.ACTIVE;

    @OneToOne
    @JoinColumn(name = Constants.DOCTOR_ID)
    private Doctor doctor;

    @Column(name = Constants.BLOOD_PRESSURE)
    private String bpRiskLevel;

    @CreationTimestamp
    @Column(name = Constants.CREATED_AT)
    private LocalDateTime createdAt;
}

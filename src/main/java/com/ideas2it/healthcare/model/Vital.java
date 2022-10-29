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
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>
 * This Vital class internally contains getter and setter
 * methods because of using lombok dependency
 * </p>
 *
 * @author Bala Ashwanth.N
 * @since 2022-10-10
 */
@Data
@Entity(name = Constants.VITAL)
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
    private String BPRiskLevel;

    @CreationTimestamp
    @Column(name = Constants.CREATED_AT)
    private LocalDateTime createdAt;
}

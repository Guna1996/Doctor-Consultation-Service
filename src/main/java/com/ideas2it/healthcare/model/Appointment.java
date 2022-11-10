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
 * This Appointment class contains details for doctors appointment with patient
 * creates entity for appointment in database
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Data
@Entity(name = Constants.APPOINTMENT)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.ID)
    private int id;

    @Column(name = Constants.SCHEDULED_AT)
    private LocalDateTime scheduledAt;

    @Column
    private String timeFormat;

    @OneToOne
    @JoinColumn(name = Constants.PATIENT_ID)
    private Patient patient;

    @OneToOne
    @JoinColumn(name = Constants.DOCTOR_ID)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = Constants.CLINIC_ID)
    private Clinic clinic;

    @Column(name = Constants.STATUS)
    private String status;

    @CreationTimestamp
    @Column(name = Constants.CREATED_AT)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = Constants.UPDATED_AT)
    private LocalDateTime updatedAt;
}

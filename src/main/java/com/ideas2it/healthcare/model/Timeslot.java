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
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * This Timeslot class internally contains getter and setter
 * methods for regarding fields because of using lombok dependency
 * </p>
 *
 * @author Bala Ashwanth.N
 * @since 2022-10-10
 */
@Data
@Entity(name = Constants.TIMESLOT)
public class Timeslot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalTime timeslot;

    @Column
    private String timeFormat;

    @CreationTimestamp
    @Column(name = Constants.CREATED_AT)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = Constants.UPDATED_AT)
    private LocalDateTime updatedAt;
}
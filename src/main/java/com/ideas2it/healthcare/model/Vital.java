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
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Calendar;

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

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @Column
    private String status = Constants.ACTIVE;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @Column(name = "blood_pressure")
    private String bloodPressure;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdAt;
}

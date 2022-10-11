/**
 * <p>
 * This is the base package for all the model classes
 * which is for doctor, clinic and patient
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.model;

import jdk.jfr.Timestamp;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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
 * This Appointment class contains details for doctors appointment with patient
 * cretaes entity for appointment in database
 * </p>
 *
 * @author  Gunaseelan K
 *
 * @version 1
 *
 * @since   2022-10-10
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "time_slot")
    private String timeSlot;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_date")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
}

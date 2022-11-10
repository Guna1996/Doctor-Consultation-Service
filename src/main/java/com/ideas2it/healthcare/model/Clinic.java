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

/**
 * <p>
 * This clinic class contains details of clinic
 * creates entity for clinic in database
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Data
@Entity(name = Constants.CLINIC)
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.ID)
    private int id;

    @Column(name = Constants.NAME)
    private String name;

    @Column(name = Constants.DOOR_NUMBER)
    private int doorNumber;

    @Column(name = Constants.STREET_NAME)
    private String streetName;

    @Column(name = Constants.CITY)
    private String city;

    @Column(name = Constants.STATE)
    private String state;

    @Column(name = Constants.PIN_CODE)
    private String pinCode;

    @Column(name = Constants.CONTACT_NUMBER, unique = true)
    private String contactNumber;

    @Column(name = Constants.STATUS)
    private String status;

    @CreationTimestamp
    @Column(name = Constants.CREATED_AT)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = Constants.UPDATED_AT)
    private LocalDateTime updatedAt;
}

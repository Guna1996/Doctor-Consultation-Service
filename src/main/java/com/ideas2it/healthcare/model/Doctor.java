/**
 * <p>
 * This is the base package for all the model classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.model;

import com.ideas2it.healthcare.common.Constants;
import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.Set;

/**
 * <p>
 * This Doctor class is a model class and this class is used
 * to transfer the object from dto layer to service
 * layer
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@Data
@Entity(name = Constants.DOCTOR)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.ID)
    private int id;

    @Column(name = Constants.NAME)
    private String name;

    @Column(name = Constants.DATE_OF_BIRTH)
    private LocalDate dateOfBirth;

    @Column(name = Constants.GENDER)
    private String gender;

    @Column(name = Constants.QUALIFICATION)
    private String qualification;

    @ManyToMany
    @JoinTable(name = Constants.DOCTOR_SPECIALIZATION,
            joinColumns = @JoinColumn(name = Constants.DOCTOR_ID),
            inverseJoinColumns = @JoinColumn(name = Constants.SPECIALIZATION_ID))
    private Set<Specialization> specializations;

    @Column(name = Constants.DATE_OF_REGISTRATION)
    private LocalDate dateOfRegistration;

    @Column(name = Constants.MOBILE_NUMBER)
    private Long mobileNumber;

    @Column(name = Constants.CITY)
    private String city;

    @Column(name = Constants.STATUS)
    private String status;

    @Column(name = Constants.CONSULTATION_FEE)
    private int consultationFee;

    @Transient
    private Integer total_entries;
}

/**
 * <p>
 * This is the base package for all the model classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ideas2it.healthCare.common.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * This Doctor class is a model class and this class is used
 * to transfer the object from dto layer to service
 * layer
 * </p>
 *
 * @author  Mohamed Jubair
 *
 * @version 1
 *
 * @since   2022-10-10
 */
@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "qualification")
    private String qualification;

    @ManyToMany
    @JoinTable(name = "doctor_specialization",
    joinColumns = @JoinColumn(name = "doctor_id"),
    inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    private Set<Specialization> specializations;

    @Column(name = "registration_year")
    private LocalDate dateOfRegistration;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "doctor")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    private List<DoctorClinic> clinics;
}

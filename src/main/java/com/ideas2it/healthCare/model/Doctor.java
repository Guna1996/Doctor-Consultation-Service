/**
 * <p>
 * This is the base package for all the model classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.model;

import com.ideas2it.healthCare.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
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

    @OneToMany
    @JoinColumn(name = "specialization_id", nullable = false)
    private List<Specialization> specializations;

    @Column(name = "registration_year")
    private LocalDate registrationYear;

    @Column(name = "mobile_number")
    private long mobileNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "status")
    private String status = Constants.ACTIVE;

    @OneToMany(mappedBy = "doctor")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    private List<Vitals> vitals;

    @OneToMany(mappedBy = "timeslot")
    private List<Timeslot> timeslots;

}

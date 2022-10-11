/**
 * <p>
 * This is the base package for all the model classes
 * which is for doctor, clinic and patient
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.model;

import com.ideas2it.doctorConsultancyService.common.Constants;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * <p>
 * This clinic class contains details of clinic
 * cretaes entity for clinic in database
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
@Table(name = "clinic")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "door_number")
    private int doorNumber;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pin_code")
    private int pinCode;

    @Pattern(regexp = Constants.MOBILE_NUMBER_REGEX, message = "type only 10 numbers")
    @NotNull(message = "mobile number shouldn't be null")
    private String contactNumber;

    @OneToMany(mappedBy = "clinic")
    private List<DoctorClinic> doctorsInClinic;

    @OneToMany(mappedBy = "clinic")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "clinic")
    private List<Vitals> vitals;
}

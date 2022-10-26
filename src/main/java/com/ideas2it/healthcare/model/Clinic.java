/**
 * <p>
 * This is the base package for all the model classes
 * which is for doctor, clinic and patient
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.model;

import com.ideas2it.healthcare.common.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <p>
 * This clinic class contains details of clinic
 * cretaes entity for clinic in database
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Getter
@Setter
@Entity(name = "clinic")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    private String pinCode;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "status")
    private String status = Constants.ACTIVE;
}

/**
 * <p>
 * This is the base package for all the model classes
 * which is for doctor, clinic and patient
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.model;

import com.ideas2it.healthcare.common.Constants;
import lombok.Data;

import javax.persistence.*;

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

    @Column(name = Constants.CONTACT_NUMBER)
    private String contactNumber;

    @Column(name = Constants.STATUS)
    private String status;
}

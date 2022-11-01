/**
 * <p>
 * This is the base package for all the model classes
 * which is for doctor, patient and clinic.
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
import javax.persistence.Transient;

/**
 * <p>
 * This Specialization class is a model class and this class is used
 * to transfer the object from dto layer to service
 * layer.
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@Data
@Entity(name = Constants.SPECIALIZATION)
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.ID)
    private int id;

    @Column(name = Constants.NAME)
    private String name;

    @Column(name = Constants.STATUS)
    private String status;

    @Transient
    private Integer total_entries;
}

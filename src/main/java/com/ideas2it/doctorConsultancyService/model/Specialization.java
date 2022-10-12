/**
 * <p>
 * This is the base package for all the model classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.model;

import com.ideas2it.doctorConsultancyService.common.Constants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * <p>
 * This Specialization class is a model class and this class is used
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
@Table(name = "specialization")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Pattern(regexp = Constants.NAME_REGEX, message = "Enter You Name in this (Firstname Secondname) format")
    private String name;

    @Column(name = "status")
    private String status;

}

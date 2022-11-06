/**
 * <p>
 * This package contains classes are Doctor clinic repository,
 * Patient repository, Doctor repository, Clinic repository,
 * Appointment repository, Feedback repository, Specialization repository,
 * Timeslot repository, Patient vital repository.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.repository;

import com.ideas2it.healthcare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

/**
 * <p>
 * Interface Patient repository extends the Jpa repository
 * and used to retrieve and delete and update
 * patient data's from the database
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    /**
     * <p>
     * This method is used to find active patient by patient id,
     * here we are passing input as id and status with help of
     * these input this method will return the active patient
     * from the database
     * </p>
     *
     * @param id     {@link Integer} is id of the patient
     * @param status {@link String} is status of patient
     * @return {@link Optional< Patient >}
     */
    Optional<Patient> findByIdAndStatus(Integer id, String status);
}

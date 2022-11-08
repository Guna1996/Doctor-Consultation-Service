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

import com.ideas2it.healthcare.model.PatientVital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * <p>
 * This Patient Vital Repository interface is a repository interface and this
 * interface extends JpaRepository which has all the methods for
 * CRUD operation
 * </p>
 *
 * @author Bala Ashwanth.N
 * @version 1
 * @since 2022-10-10
 */
@Repository
@Transactional
public interface PatientVitalRepository extends JpaRepository<PatientVital, Integer> {

    /**
     * <p>
     * This method is used to find active vital by vital id
     * </p>
     *
     * @param id     {@link Integer} is id of the vital
     * @param status {@link String} is status of vital
     * @return {@link Optional<PatientVital>}
     */
    Optional<PatientVital> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     * This method is used to find active vitals by patient id and using
     * pagination which can get only the required number of rows.
     * </p>
     *
     * @param patientId {@link Integer} is id of the patient
     * @param status    {@link String} is status of vital
     * @param pageable  {@link Pageable} contains page number and number of rows required
     * @return {@link Page<PatientVital>}
     */
    Page<PatientVital> findByPatientIdAndStatus(
            Integer patientId, String status, Pageable pageable);

    /**
     * <p>
     * This method is used to find active vitals by patient id
     * </p>
     *
     * @param patientId {@link Integer} is id of the patient
     * @param status    {@link String} is status of vital
     * @return {@link Integer}
     */
    Integer countByPatientIdAndStatus(Integer patientId, String status);
}
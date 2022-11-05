/**
 * <p>
 * This package contains classes are DoctorClinicRepository,
 * PatientRepository, DoctorRepository, ClinicRepository,
 * AppointmentRepository, FeedbackRepository, SpecializationRepository,
 * TimeslotRepository, VitalsRepository.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.repository;

import com.ideas2it.healthcare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <p>
 * Interface PatientRepository extends he  JpaRepository
 * and used to retrieve and delete and update
 * patient data's from database
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
     * This method is used to find active patient by patient id
     * </p>
     *
     * @param id     {@link Integer} is id of the patient
     * @param status {@link String} is status of patient
     * @return {@link Optional< Patient >}
     */
    Optional<Patient> findByIdAndStatus(Integer id, String status);
}

/**
 * <p>
 * This package contains classes are DoctorClinicRepository,
 * PatientRepository, DoctorRepository, ClinicRepository,
 * AppointmentRepository, FeedbackRepository, SpecializationRepository,
 * TimeslotRepository, VitalsRepository.
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.repo;

import com.ideas2it.healthcare.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Interface ClinicRepository extends the  JpaRepository
 * and used to retrieve and delete and update
 * clinic data's from database
 * </p>
 *
 * @author Gunaseelan K
 *
 * @version 1
 *
 * @since 2022-07-18
 */
@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    Optional<Clinic> findByIdAndStatus(int id, String status);

    Boolean existsByIdAndStatus(int id, String status);

    List<Clinic> findAllByStatus(String active);
}


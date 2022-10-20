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

import com.ideas2it.healthcare.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * <p>
 * Interface PatientRepository extends he  JpaRepository
 * and used to retrieve and delete and update
 * patient data's from database
 * </p>
 *
 * @author Ramachandran
 *
 * @version 1
 *
 * @since 2022-10-10
 */
@Repository
@Transactional
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByIdAndStatus(Integer id, String status);

    Page<Patient> findAllByStatus(String status, Pageable pageable);

    @Modifying
    @Query("update patient set status='inactive' where id=?1 and status = 'active'")
    Integer deletePatientById(int id);

}

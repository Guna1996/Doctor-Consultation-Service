/**
 * <p>
 * This package contains classes are DoctorClinicRepository,
 * PatientRepository, DoctorRepository, ClinicRepository,
 * AppointmentRepository, FeedbackRepository, SpecializationRepository,
 * TimeslotRepository, VitalsRepository.
 * </p>
 * <p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.repo;

import com.ideas2it.healthcare.model.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
 * @version 1
 * @since 2022-07-18
 */
@Repository
@Transactional
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {

    Optional<Clinic> findByIdAndStatus(int id, String status);

    Boolean existsByIdAndStatus(int id, String status);

    List<Clinic> findAllByStatus(String active);

    @Modifying
    @Query("update clinic set status='inactive' where id=?1 and status = 'active'")
    Integer deleteClinicById(int id);

    Page<Clinic> findAllByStatus(String active, Pageable pageable);
}


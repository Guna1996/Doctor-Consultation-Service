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

    /**
     * <p>
     * This method is used to find active clinic by clinic id
     * </p>
     *
     * @param id {@link Integer} is id of the clinic
     * @param status {@link String} is status of clinic table
     * @return {@link Optional<Clinic>}
     */
    Optional<Clinic> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     * This method is used to delete clinic by id
     * and set the status as inactive using query
     * </p>
     *
     * @param id {@link Integer} is id of the clinic
     * @return {@link Integer}
     */
    @Modifying
    @Query("update clinic set status = 'inactive' where id=?1 and status = 'active'")
    Integer deleteClinicById(Integer id);

    /**
     * <p>
     * This method is used to find all active clinics
     * </p>
     *
     * @param status {@link String} is status of clinic table
     * @param pageable {@link Pageable} contains page number and number of rows required
     * @return {@link Page<Clinic>}
     */
    Page<Clinic> findAllByStatus(String status, Pageable pageable);

    List<Clinic> findAllByStatus(String status);

}


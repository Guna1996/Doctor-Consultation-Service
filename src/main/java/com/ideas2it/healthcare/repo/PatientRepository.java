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
import java.util.List;
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

    /**
     * <p>
     * This method is used to retrieve patient by
     * using patient id and patient status and send back to the
     * PatientService
     * </p>
     *
     * @param id     - id of the patient
     * @param status - status of the patient in databse
     * @return patient
     */
    Optional<Patient> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     *  This method is used to retrieve patients by
     *  using patient status and send back to the
     *  PatientService
     * </p>
     *
     * @param status - status of the patient
     *
     * @return List<patient>
     */
    Page<Patient> findAllByStatus(String status, Pageable pageable);
    
    @Modifying
    @Query("update patient set status='inactive' where id=?1")
    Integer deletePatiendById(int id);

}

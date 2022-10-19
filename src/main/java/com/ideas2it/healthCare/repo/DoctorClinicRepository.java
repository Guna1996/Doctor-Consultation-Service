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
package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.DoctorClinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Interface DoctorClinicRepository extends the JpaRepository
 * and used to retrieve and delete and update
 * DoctorClinic data's from database
 * </p>
 *
 * @author Ramachandran
 *
 * @version 1
 *
 * @since 2022-07-18
 */
@Repository
@Transactional
public interface DoctorClinicRepository extends JpaRepository<DoctorClinic, Integer> {

    /**
     * <p>
     * This findAllByStatus abstract method is used for
     * get all the active doctor from the DoctorClinic
     * table
     * </p>
     *
     * @param status - status of the doctor in clinic side
     *
     * @return List<DoctorClinic>
     */
    List<DoctorClinic> findAllByStatus(String status);

    /**
     * <p>
     * This findByIdAndStatus abstract method is used to
     * getting doctor object in the doctorclinic table by getting
     * doctor id from the user and also it will return only the
     * active doctor in the doctorclinic table
     * </p>
     *
     * @param id - id of the doctor in DoctorClinic table
     *
     * @param status - status of the doctor
     *
     * @return DoctorClinic
     */
    Optional<DoctorClinic> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     * This existsByIdAndStatus abstract method is used to
     * check wheather a doctor is available or not in clinic
     * side by getting id and status
     * </p>
     *
     * @param id - id of the doctor are clinic side
     *
     * @param status - status of the doctor
     *
     * @return boolean
     */
    Boolean existsByIdAndStatus(int id, String status);

    Optional<DoctorClinic> findByDoctorIdAndClinicIdAndStatus(int doctorId, int clinicId, String status);

    @Modifying
    @Query("update doctorClinic set status='inactive' where id=?1")
    Integer deleteDoctorClinicById(int id);
}

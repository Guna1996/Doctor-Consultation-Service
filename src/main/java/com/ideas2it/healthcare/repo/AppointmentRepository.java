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

<<<<<<< HEAD:src/main/java/com/ideas2it/healthcare/repo/AppointmentRepository.java
import com.ideas2it.healthcare.model.Appointment;
=======
import com.ideas2it.healthCare.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> 0ca24ebd26b8c6544fd9b5e1a2842c87732d1ed0:src/main/java/com/ideas2it/healthCare/repo/AppointmentRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Interface AppointmentRepository extends the  JpaRepository
 * and used to retrieve and delete and update
 * appointment data's from database
 * </p>
 *
 * @author Gunaseelan K
 *
 * @version 1
 *
 * @since 2022-07-18
 */
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Page<Appointment> findAllByStatus(String status, Pageable pageable);

    Optional<Appointment> findByIdAndStatus(int id, String status);

    Optional<Appointment> findByScheduledOnAndStatus(LocalDateTime scheduledOn, String status);

    Optional<Appointment> findByDoctorIdAndScheduledOnAndStatus(int id, LocalDateTime dateTime, String active);

    @Modifying
    @Query("update appointment set status='inactive' where id=?1")
    Integer deleteAppointmentById(int id);
}

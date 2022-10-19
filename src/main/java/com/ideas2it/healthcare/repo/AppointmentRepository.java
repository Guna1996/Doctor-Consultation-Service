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

import com.ideas2it.healthcare.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findAllByStatus(String status);

    Optional<Appointment> findByIdAndStatus(int id, String status);

    Optional<Appointment> findByScheduledOnAndStatus(LocalDateTime scheduledOn, String status);

    Optional<Appointment> findByDoctorIdAndScheduledOnAndStatus(int id, LocalDateTime dateTime, String active);
}

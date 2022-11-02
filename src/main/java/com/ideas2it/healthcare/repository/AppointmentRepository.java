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

import com.ideas2it.healthcare.model.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
 * @version 1
 * @since 2022-07-18
 */
@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    /**
     * <p>
     * This method is used to find active appointment by doctor id and scheduled datetime
     * </p>
     *
     * @param id       {@link Integer} is id of the doctor
     * @param dateTime {@link LocalDateTime} is date and time of appointment
     * @param status   {@link String} is status of appointment table
     * @return {@link Optional<Appointment>}
     */
    Optional<Appointment> findByDoctorIdAndScheduledOnAndStatus(Integer id, LocalDateTime dateTime, String status);

    /**
     * <p>
     * This method is used to delete appointment by id
     * and set the status as inactive using query
     * </p>
     *
     * @param id {@link Integer} is id of the appointment
     * @return {@link Integer}
     */
    @Modifying
    @Query("update appointment set status='inactive' where id=?1 and status = 'active'")
    Integer deleteAppointmentById(Integer id);

    /**
     * <p>
     * This method is used to find active appointments by doctor id and pagination
     * </p>
     *
     * @param id       {@link Integer} is id of the doctor
     * @param status   {@link String} is status of appointment table
     * @param pageable {@link Pageable} contains page number and number of rows required
     * @return {@link Page<Appointment>}
     */
    Page<Appointment> findByDoctorIdAndStatus(Integer id, String status, Pageable pageable);

    /**
     * <p>
     * This method is used to find active appointments by patient id and pagination
     * </p>
     *
     * @param id       {@link Integer} is id of the patient
     * @param status   {@link String} is status of appointment table
     * @param pageable {@link Pageable} contains page number and number of rows required
     * @return {@link Page<Appointment>}
     */
    Page<Appointment> findByPatientIdAndStatus(Integer id, String status, Pageable pageable);

    Integer countByDoctorIdAndStatus(Integer id, String status);

    Integer countByPatientIdAndStatus(Integer id, String status);
}

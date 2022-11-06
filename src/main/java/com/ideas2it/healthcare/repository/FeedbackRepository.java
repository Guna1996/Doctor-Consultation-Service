/**
 * <p>
 * This package contains classes are Doctor clinic repository,
 * Patient repository, Doctor repository, Clinic repository,
 * Appointment repository, Feedback repository, Specialization repository,
 * Timeslot repository, Patient vital repository.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.repository;

import com.ideas2it.healthcare.model.Feedback;
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
 * This Feedback repository interface is a repository interface and this
 * interface extends JpaRepository which has all the methods for
 * crud operation
 * </p>
 *
 * @author Bala Ashwanth.N
 * @version 1
 * @since 2022-10-10
 */
@Repository
@Transactional
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    /**
     * <p>
     * This method is used to find active feedback by feedback id
     * </p>
     *
     * @param id     {@link Integer} is id of the feedback
     * @param status {@link String} is status of feedback
     * @return {@link Optional<Feedback>}
     */
    Optional<Feedback> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     * This method is used to find active feedback by doctor id and using
     * pagination which can get only the required number of rows.
     * </p>
     *
     * @param doctorId {@link Integer} is id of the doctor
     * @param status   {@link String} is status of feedback
     * @param pageable {@link Pageable} contains page number and number of rows required
     * @return {@link Page<Feedback>}
     */
    Page<Feedback> findByDoctorIdAndStatus(
            Integer doctorId, String status, Pageable pageable);

    /**
     * <p>
     * This method is used to delete feedback by id
     * and set the status as inactive using query
     * </p>
     *
     * @param id {@link Integer} is id of the feedback
     * @return {@link Integer}
     */
    @Modifying
    @Query("update feedback set status = 'inactive' where id=?1")
    Integer removeFeedbackById(Integer id);

    /**
     * <p>
     * This method is used to find active the count of feedback by doctor id
     * </p>
     *
     * @param doctorId {@link Integer} is id of the doctor
     * @param status   {@link String} is status of feedback
     * @return {@link Integer}
     */
    Integer countByDoctorIdAndStatus(Integer doctorId, String status);
}

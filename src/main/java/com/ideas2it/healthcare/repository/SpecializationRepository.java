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

import com.ideas2it.healthcare.model.Specialization;
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
 * This Specialization repository interface is a repository interface and this
 * interface extends Jpa repository which has all the methods for
 * crud operation
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@Repository
@Transactional
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

    /**
     * <p>
     * This method is used to find active specialization
     * by specialization id from the database
     * </p>
     *
     * @param id     {@link Integer} is id of the specialization
     * @param status {@link String} is status of specialization
     * @return {@link Optional<Specialization>}
     */
    Optional<Specialization> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     * This method is used to find all active specializations using
     * pagination which can get only the required number of rows.
     * </p>
     *
     * @param status   {@link String} is status of specialization
     * @param pageable {@link Pageable} contains page number and number of rows required
     * @return {@link Page<Specialization>}
     */
    Page<Specialization> findAllByStatus(String status, Pageable pageable);

    /**
     * <p>
     * This method is used to delete specialization by id
     * and set the status as inactive using query
     * </p>
     *
     * @param id {@link Integer} is id of the specialization
     * @return {@link Integer}
     */
    @Modifying
    @Query("update specialization set status = 'inactive' where id=?1 and status ='active'")
    Integer removeSpecializationById(Integer id);

    /**
     * <p>
     * This method is used to find all active specializations
     * </p>
     *
     * @param status {@link String} is status of specialization
     * @return {@link Integer}
     */
    Integer countByStatus(String status);
}

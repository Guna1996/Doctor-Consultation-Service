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

import com.ideas2it.healthcare.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

/**
 * <p>
 * This Timeslot repository interface is a repository interface and this
 * interface extends Jpa repository which has all the methods for
 * crud operation
 * </p>
 *
 * @author Bala Ashwanth.N
 * @version 1
 * @since 2022-10-10
 */
@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Integer> {

    /**
     * <p>
     * This method is used to find timeslot by timeslot name
     * from the database
     * </p>
     *
     * @param timeslot {@link LocalTime} is time of the timeslot
     * @return {@link Timeslot}
     */
    Timeslot findByTimeslotAndTimeFormat(LocalTime timeslot, String timeFormat);
}

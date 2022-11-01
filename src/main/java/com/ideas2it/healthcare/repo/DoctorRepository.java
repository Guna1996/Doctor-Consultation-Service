/**
 * <p>
 * This is the base package for all the repository classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.repo;

import com.ideas2it.healthcare.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * This DoctorRepository interface is a repository interface and this
 * interface extends JpaRepository which has all the methods for
 * CRUD operation
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@Transactional
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    /**
     * <p>
     * This method is used to find active doctor by doctor id
     * </p>
     *
     * @param id {@link Integer} is id of the doctor
     * @param status {@link String} is status of doctor
     * @return {@link Optional<Doctor>}
     */
    Optional<Doctor> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     * This method is used to find all active doctors
     * </p>
     *
     * @param status {@link String} is status of doctor
     * @param pageable {@link Pageable} contains page number and number of rows required
     * @return {@link Page<Doctor>}
     */
    Page<Doctor> findAllByStatus(String status, Pageable pageable);

    /**
     * <p>
     * This method is used to delete doctor by id
     * and set the status as inactive using query
     * </p>
     *
     * @param id {@link Integer} is id of the doctor
     * @return {@link Integer}
     */
    @Modifying
    @Query("update doctor set status = 'inactive' where id=?1 and status ='active'")
    Integer deleteDoctorById(Integer id);

    List<Doctor> findAllByStatus(String status);
}

/**
 * <p>
 * This is the base package for all the repository classes
 * which is for doctor, patient and clinic
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

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * This SpecializationRepository interface is a repository interface and this
 * interface extends JpaRepository which has all the methods for
 * CRUD operation
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */
@Transactional
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {

    /**
     * <p>
     * This method is used to find active specialization
     * by specialization id
     * </p>
     *
     * @param id     {@link Integer} is id of the specialization
     * @param status {@link String} is status of specialization
     * @return {@link Optional<Specialization>}
     */
    Optional<Specialization> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     * This method is used to find all active specializations by pagination
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
    Integer deleteSpecializationById(Integer id);

    /**
     * <p>
     * This method is used to find all active specializations
     * </p>
     *
     * @param status   {@link String} is status of specialization
     * @return {@link List<Specialization>}
     */
    List<Specialization> findAllByStatus(String status);
}

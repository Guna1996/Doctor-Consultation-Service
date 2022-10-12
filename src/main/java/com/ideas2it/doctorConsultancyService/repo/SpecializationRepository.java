/**
 * <p>
 * This is the base package for all the repository classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.doctorConsultancyService.repo;

import com.ideas2it.doctorConsultancyService.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * This SpecializationRepository interface is a repository interface and this
 * interface extends JpaRepository which has all the methods for
 * CRUD operation
 * </p>
 *
 * @author  Mohamed Jubair
 *
 * @version 1
 *
 * @since   2022-10-10
 */
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    Specialization findByIdAndStatus(int id, String active);
}

/**
 * <p>
 * This is the base package for all the repository classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.dto.DoctorDto;
import com.ideas2it.healthCare.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * This DoctorRepository interface is a repository interface and this
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
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByIdAndStatus(int id, String active);

    List<Doctor> findAllByStatus(String active);
}

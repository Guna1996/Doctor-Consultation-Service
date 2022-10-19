/**
 * <p>
 * This is the base package for all the repository classes
 * which is for doctor, patient and clinic
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.repo;

<<<<<<< HEAD:src/main/java/com/ideas2it/healthcare/repo/DoctorRepository.java
import com.ideas2it.healthcare.model.Doctor;
=======
import com.ideas2it.healthCare.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> 0ca24ebd26b8c6544fd9b5e1a2842c87732d1ed0:src/main/java/com/ideas2it/healthCare/repo/DoctorRepository.java
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
 * @author  Mohamed Jubair
 *
 * @version 1
 *
 * @since   2022-10-10
 */
@Transactional
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByIdAndStatus(int id, String active);

    Boolean existsByIdAndStatus(int id, String status);
    
    Page<Doctor> findAllByStatus(String active, Pageable pageable);

    @Modifying
    @Query("update doctor set status='inactive' where id=?1")
    Integer deleteDoctorById(int id);

}

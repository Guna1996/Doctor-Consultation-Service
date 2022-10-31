package com.ideas2it.healthcare.repo;

import com.ideas2it.healthcare.model.Vital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VitalsRepository extends JpaRepository<Vital, Integer> {

    /**
     * <p>
     * This method is used to find active vital by vital id
     * </p>
     *
     * @param id {@link Integer} is id of the vital
     * @param status {@link String} is status of vital
     * @return {@link Optional<Vital>}
     */
    Optional<Vital> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     * This method is used to find active vitals by patient id
     * </p>
     *
     * @param patientId {@link Integer} is id of the patient
     * @param status {@link String} is status of vital
     * @param pageable {@link Pageable} contains page number and number of rows required
     * @return {@link Page<Vital>}
     */
    Page<Vital> findByPatientIdAndStatus(Integer patientId, String status, Pageable pageable);
}
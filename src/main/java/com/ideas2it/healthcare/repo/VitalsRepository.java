package com.ideas2it.healthcare.repo;

import com.ideas2it.healthcare.model.Vital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface VitalsRepository extends JpaRepository<Vital, Integer> {

    Optional<Vital> findByIdAndStatus(int id, String status);

    Page<Vital> findAllByStatus(String status, Pageable pageable);

    Page<Vital> findByPatientIdAndStatus(int patientId, String status, Pageable pageable);
}
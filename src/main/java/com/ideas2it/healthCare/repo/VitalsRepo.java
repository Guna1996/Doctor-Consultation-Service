package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.Vitals;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VitalsRepo extends JpaRepository<Vitals, Integer> {

    Optional<Vitals> findByIdAndStatus(int id, String status);

    Page<Vitals> findAllByStatus(String status, Pageable pageable);

    Boolean existsByIdAndStatus(int id, String status);

    Page<Vitals> findByPatientId(int patientId, Pageable pageable);



}
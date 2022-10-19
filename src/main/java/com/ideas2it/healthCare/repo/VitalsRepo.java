package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VitalsRepo extends JpaRepository<Vitals, Integer> {

    Optional<Vitals> findByIdAndStatus(int id, String status);

    List<Vitals> findAllByStatus(String status);

    Boolean existsByIdAndStatus(int id, String status);

    List<Vitals> findByPatientId(int patientId);



}
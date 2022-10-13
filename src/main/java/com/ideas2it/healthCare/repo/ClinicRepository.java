package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.Clinic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicRepository extends CrudRepository<Clinic, Integer> {
    Optional<Clinic> findByIdAndStatus(int id, String status);

    List<Clinic> findAllByStatus(String active);
}


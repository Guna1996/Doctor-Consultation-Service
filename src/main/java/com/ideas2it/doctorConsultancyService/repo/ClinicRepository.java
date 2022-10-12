package com.ideas2it.doctorConsultancyService.repo;

import com.ideas2it.doctorConsultancyService.model.Clinic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface ClinicRepository extends CrudRepository<Clinic, Integer> {
    Optional<Clinic> findByIdAndStatus(int id, String status);
}


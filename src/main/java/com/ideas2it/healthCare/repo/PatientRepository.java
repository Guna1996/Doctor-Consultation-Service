package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    Patient deleteByIdAndStatus(Integer id, String status);
    Patient findByIdAndStatus(Integer id, String status);
}

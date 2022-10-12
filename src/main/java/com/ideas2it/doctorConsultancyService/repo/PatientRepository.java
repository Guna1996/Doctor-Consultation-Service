package com.ideas2it.doctorConsultancyService.repo;

import com.ideas2it.doctorConsultancyService.exception.NotFoundException;
import com.ideas2it.doctorConsultancyService.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    Patient deleteByIdAndStatus(Integer id, String status);
    Patient findByIdAndStatus(Integer id, String status);
}

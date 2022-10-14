package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.DoctorClinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorClinicRepository extends JpaRepository<DoctorClinic, Integer> {
    List<DoctorClinic> findAllByStatus(String status);

    DoctorClinic findByIdAndStatus(Integer id, String status);
}

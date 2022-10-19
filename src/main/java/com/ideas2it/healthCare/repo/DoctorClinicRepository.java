package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.DoctorClinic;
import com.ideas2it.healthCare.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorClinicRepository extends JpaRepository<DoctorClinic, Integer> {
    List<DoctorClinic> findAllByStatus(String status);

    DoctorClinic findByIdAndStatus(Integer id, String status);

    Boolean existsByIdAndStatus(int id, String status);

    Optional<DoctorClinic> findByDoctorIdAndClinicIdAndStatus(int doctorId, int clinicId, String status);
}

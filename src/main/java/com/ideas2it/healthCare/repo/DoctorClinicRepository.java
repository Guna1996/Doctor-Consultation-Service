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

    @Query(value = "Select * from doctor_clinic dc where dc.doctor_id = ?1 and dc.clinic_id = ?2 and status = ?3",
            nativeQuery = true)
    Optional<DoctorClinic> findBlyDoctorIdAndClinicIdAndStatus(int doctorId, int clinicId, String status);
}

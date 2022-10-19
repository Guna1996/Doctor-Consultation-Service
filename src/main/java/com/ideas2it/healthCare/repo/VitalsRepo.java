package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface VitalsRepo extends JpaRepository<Vitals, Integer> {

    Optional<Vitals> findByIdAndStatus(int id, String status);

    List<Vitals> findAllByStatus(String status);

    Boolean existsByIdAndStatus(int id, String status);

    List<Vitals> findByPatientId(int patientId);

    @Modifying
    @Query("update vitals set status='inactive' where id=?1")
    Integer deleteVitalsById(int id);
}
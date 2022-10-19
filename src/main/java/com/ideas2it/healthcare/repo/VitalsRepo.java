package com.ideas2it.healthcare.repo;

<<<<<<< HEAD:src/main/java/com/ideas2it/healthcare/repo/VitalsRepo.java
import com.ideas2it.healthcare.model.Vitals;
import org.springframework.data.jpa.repository.JpaRepository;
=======
import com.ideas2it.healthCare.model.Vitals;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
>>>>>>> 0ca24ebd26b8c6544fd9b5e1a2842c87732d1ed0:src/main/java/com/ideas2it/healthCare/repo/VitalsRepo.java
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface VitalsRepo extends JpaRepository<Vitals, Integer> {

    Optional<Vitals> findByIdAndStatus(int id, String status);

    Page<Vitals> findAllByStatus(String status, Pageable pageable);

    Boolean existsByIdAndStatus(int id, String status);

    Page<Vitals> findByPatientId(int patientId, Pageable pageable);

    @Modifying
    @Query("update vitals set status='inactive' where id=?1")
    Integer deleteVitalsById(int id);
}
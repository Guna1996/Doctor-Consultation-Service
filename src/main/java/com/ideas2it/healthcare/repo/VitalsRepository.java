package com.ideas2it.healthcare.repo;

import com.ideas2it.healthcare.model.Vital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface VitalsRepository extends JpaRepository<Vital, Integer> {

    Optional<Vital> findByIdAndStatus(int id, String status);

    Page<Vital> findAllByStatus(String status, Pageable pageable);

    Boolean existsByIdAndStatus(int id, String status);

    Page<Vital> findByPatientId(int patientId, Pageable pageable);

//    @Modifying
//    @Query("update vital set status='inactive' where id=?1 and status = 'active'")
//    Integer deleteVitalsById(int id);
}
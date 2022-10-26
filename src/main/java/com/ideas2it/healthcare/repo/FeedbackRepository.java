package com.ideas2it.healthcare.repo;

import com.ideas2it.healthcare.model.Feedback;
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
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    Optional<Feedback> findByIdAndStatus(int id, String status);

    Page<Feedback> findByDoctorIdAndStatus(int doctorId, String status, Pageable pageable);

    Boolean existsByIdAndStatus(int id, String status);

    @Modifying
    @Query("update feedback set status='inactive' where id=?1")
    Integer deleteSpecializationById(int id);

}

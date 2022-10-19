package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

    Optional<Feedback> findByIdAndStatus(int id, String status);

    Page<Feedback> findAllByStatus(String status, Pageable pageable);

    Boolean existsByIdAndStatus(int id, String status);

}

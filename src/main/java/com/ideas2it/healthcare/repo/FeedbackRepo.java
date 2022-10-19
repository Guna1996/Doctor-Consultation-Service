package com.ideas2it.healthcare.repo;

import com.ideas2it.healthcare.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

    Optional<Feedback> findByIdAndStatus(int id, String status);

    List<Feedback> findAllByStatus(String status);

    Boolean existsByIdAndStatus(int id, String status);

}

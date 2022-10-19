package com.ideas2it.healthcare.repo;

<<<<<<< HEAD:src/main/java/com/ideas2it/healthcare/repo/FeedbackRepo.java
import com.ideas2it.healthcare.model.Feedback;
=======
import com.ideas2it.healthCare.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> 0ca24ebd26b8c6544fd9b5e1a2842c87732d1ed0:src/main/java/com/ideas2it/healthCare/repo/FeedbackRepo.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

    Optional<Feedback> findByIdAndStatus(int id, String status);

    Page<Feedback> findAllByStatus(String status, Pageable pageable);

    Boolean existsByIdAndStatus(int id, String status);

    @Modifying
    @Query("update feedback set status='inactive' where id=?1")
    Integer deleteSpecializationById(int id);

}

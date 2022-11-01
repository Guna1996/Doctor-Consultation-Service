package com.ideas2it.healthcare.repo;

import com.ideas2it.healthcare.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    /**
     * <p>
     * This method is used to find active feedback by feedback id
     * </p>
     *
     * @param id     {@link Integer} is id of the feedback
     * @param status {@link String} is status of feedback
     * @return {@link Optional<Feedback>}
     */
    Optional<Feedback> findByIdAndStatus(Integer id, String status);

    /**
     * <p>
     * This method is used to find active feedback by doctor id
     * </p>
     *
     * @param doctorId {@link Integer} is id of the doctor
     * @param status   {@link String} is status of feedback
     * @param pageable {@link Pageable} contains page number and number of rows required
     * @return {@link Page<Feedback>}
     */
    Page<Feedback> findByDoctorIdAndStatus(Integer doctorId, String status, Pageable pageable);

    /**
     * <p>
     * This method is used to delete feedback by id
     * and set the status as inactive using query
     * </p>
     *
     * @param id {@link Integer} is id of the feedback
     * @return {@link Integer}
     */
    @Modifying
    @Query("update feedback set status = 'inactive' where id=?1")
    Integer deleteSpecializationById(Integer id);

    List<Feedback> findByDoctorIdAndStatus(Integer doctorId, String status);
}

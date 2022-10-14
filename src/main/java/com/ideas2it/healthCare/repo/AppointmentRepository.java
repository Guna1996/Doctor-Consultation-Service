package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    List<Appointment> findAllByStatus(String status);

    Optional<Appointment> findByIdAndStatus(int id, String status);

    Optional<Object> findByScheduledOnAndStatus(LocalDateTime scheduledOn, String status);
}

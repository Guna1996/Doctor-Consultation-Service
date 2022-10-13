package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    List<Appointment> findAllByStatus(String status);

    Optional<Object> findByIdAndStatus(int id, String status);
}

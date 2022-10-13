package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

}

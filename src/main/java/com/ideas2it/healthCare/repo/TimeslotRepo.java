package com.ideas2it.healthCare.repo;

import com.ideas2it.healthCare.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeslotRepo extends JpaRepository<Timeslot, Integer> {

}

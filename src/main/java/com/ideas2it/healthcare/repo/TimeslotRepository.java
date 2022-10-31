package com.ideas2it.healthcare.repo;

import com.ideas2it.healthcare.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Integer> {
}

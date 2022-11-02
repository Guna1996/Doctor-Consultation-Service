package com.ideas2it.healthcare.repository;

import com.ideas2it.healthcare.model.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Integer> {
}

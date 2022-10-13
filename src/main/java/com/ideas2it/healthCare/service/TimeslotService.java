package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.TimeslotDto;

import java.util.List;

public interface TimeslotService {
    TimeslotDto addTimeslot(TimeslotDto timeslotDto);

    TimeslotDto updateTimeslot(TimeslotDto timeslotDto);

    TimeslotDto getTimeslotById(int id);

    List<TimeslotDto> getTimeslots();

    String deleteTimeslot(int id);
}

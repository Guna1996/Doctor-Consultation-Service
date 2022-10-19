package com.ideas2it.healthcare.service;

import com.ideas2it.healthcare.dto.TimeslotDto;

import java.util.List;

public interface TimeslotService {
    TimeslotDto addTimeslot(TimeslotDto timeslotDto);

    TimeslotDto updateTimeslot(TimeslotDto timeslotDto);

    TimeslotDto getTimeslotById(int id);

    List<TimeslotDto> getTimeslots();

    String deleteTimeslot(int id);

    boolean isTimeslotAvailable(int id);
}

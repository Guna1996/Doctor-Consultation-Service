package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.TimeslotMapper;
import com.ideas2it.healthcare.model.Timeslot;
import com.ideas2it.healthcare.repo.TimeslotRepo;
import com.ideas2it.healthcare.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeslotServiceImpl implements TimeslotService {

    @Autowired
    private TimeslotRepo timeslotRepo;


    public TimeslotDto addTimeslot(TimeslotDto timeslotDto) {
        return TimeslotMapper.toDto(timeslotRepo.save(TimeslotMapper.fromDto(timeslotDto)));
    }

    public TimeslotDto updateTimeslot(TimeslotDto timeslotDto) {
        TimeslotDto timeslotDtoToReturn = null;
        if (timeslotRepo.existsById(timeslotDto.getId())) {
            timeslotDtoToReturn = TimeslotMapper.toDto(timeslotRepo.save(TimeslotMapper.fromDto(timeslotDto)));
        }
        else {
            throw new NotFoundException("The data doesn't exist");
        }
        return timeslotDtoToReturn;
    }

    public TimeslotDto getTimeslotById(int id) {
        Timeslot timeslot = timeslotRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Timeslot not found"));
        return TimeslotMapper.toDto(timeslot);
    }

    public List<TimeslotDto> getTimeslots(int pageNumber, int totalRows) {
        List<TimeslotDto> timeslotsDto = null;
        List<Timeslot> timeslots = timeslotRepo.findAll(PageRequest.of(pageNumber, totalRows)).toList();
        if (!timeslots.isEmpty()) {
            timeslotsDto = new ArrayList<>();
            for (Timeslot timeslot : timeslots) {
                timeslotsDto.add(TimeslotMapper.toDto(timeslot));
            }
        }
        else {
            throw new NotFoundException("Data is empty");
        }
        return timeslotsDto;
    }

    public String deleteTimeslot(int id) {
        if (timeslotRepo.existsById(id)) {
            timeslotRepo.deleteById(id);
        }
        else {
            throw new NotFoundException("Feedback not found");
        }
        return "Deleted Successfully";
    }

    public boolean isTimeslotAvailable(int id) {
        Timeslot  timeslot = timeslotRepo.findById(id).orElseThrow(() -> new NotFoundException("id not found"));

        return timeslot != null;

    }
}

package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.dto.TimeslotDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.TimeslotMapper;
import com.ideas2it.healthCare.model.Timeslot;
import com.ideas2it.healthCare.repo.TimeslotRepo;
import com.ideas2it.healthCare.service.TimeslotService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeslotServiceImpl implements TimeslotService {

    private final TimeslotRepo timeslotRepo;

    public TimeslotServiceImpl(TimeslotRepo timeslotRepo) {
        this.timeslotRepo = timeslotRepo;
    }

    @Override
    public TimeslotDto addTimeslot(TimeslotDto timeslotDto) {
        return TimeslotMapper.toDto(timeslotRepo.save(TimeslotMapper.fromDto(timeslotDto)));
    }

    @Override
    public TimeslotDto updateTimeslot(TimeslotDto timeslotDto) {
        if (timeslotRepo.existsById(timeslotDto.getId())) {
            return TimeslotMapper.toDto(timeslotRepo.save(TimeslotMapper.fromDto(timeslotDto)));
        }
        else {
            throw new NotFoundException("The data doesn't exist");
        }
    }

    @Override
    public TimeslotDto getTimeslotById(int id) {
        Timeslot timeslot = timeslotRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Timeslot not found"));
        return TimeslotMapper.toDto(timeslot);
    }

    @Override
    public List<TimeslotDto> getTimeslots() {
        List<Timeslot> timeslots = timeslotRepo.findAll();
        if (!timeslots.isEmpty()) {
            List<TimeslotDto> timeslotsDto = new ArrayList<>();
            for (Timeslot timeslot : timeslots) {
                timeslotsDto.add(TimeslotMapper.toDto(timeslot));
            }
            return timeslotsDto;
        }
        else {
            throw new NotFoundException("Data is empty");
        }
    }

    @Override
    public String deleteTimeslot(int id) {
        if (timeslotRepo.existsById(id)) {
            timeslotRepo.deleteById(id);
            return "Deleted Successfully";
        }
        else {
            throw new NotFoundException("Feedback not found");
        }
    }

    @Override
    public boolean isTimeslotAvailable(int id) {
        Timeslot  timeslot = timeslotRepo.findById(id).orElseThrow(() -> new NotFoundException("id not found"));

        return timeslot != null;

    }
}

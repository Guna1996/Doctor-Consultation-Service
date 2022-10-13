package com.ideas2it.healthCare.service.impl;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ideas2it.healthCare.dto.TimeslotDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.model.Timeslot;
import com.ideas2it.healthCare.repo.TimeslotRepo;
import com.ideas2it.healthCare.service.TimeslotService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeslotServiceImpl implements TimeslotService {

    private final TimeslotRepo timeslotRepo;
    private final ModelMapper modelMapper;
    @Override
    public TimeslotDto addTimeslot(TimeslotDto timeslotDto) {
        return modelMapper.map(timeslotRepo.save(modelMapper.map(timeslotDto, Timeslot.class)), TimeslotDto.class);
    }

    @Override
    public TimeslotDto updateTimeslot(TimeslotDto timeslotDto) {
        if (timeslotRepo.existsById(timeslotDto.getId())) {
            return modelMapper.map(timeslotRepo.save(modelMapper.map(timeslotDto, Timeslot.class)),TimeslotDto.class);
        }
        else {
            throw new NotFoundException("The data doesn't exist");
        }
    }

    @Override
    public TimeslotDto getTimeslotById(int id) {
        Timeslot timeslot = timeslotRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Timeslot not found"));
        return modelMapper.map(timeslot, TimeslotDto.class);
    }

    @Override
    public List<TimeslotDto> getTimeslots() {
        List<Timeslot> timeslots = timeslotRepo.findAll();
        if (!timeslots.isEmpty()) {
            List<TimeslotDto> timeslotsDto = new ArrayList<>();
            for (Timeslot timeslot : timeslots) {
                timeslotsDto.add(modelMapper.map(timeslot, TimeslotDto.class));
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
        Timeslot  timeslot = timeslotRepo.getById(id);
        if(timeslot != null) {
            return true;
        } else {
            return false;
        }

    }
}

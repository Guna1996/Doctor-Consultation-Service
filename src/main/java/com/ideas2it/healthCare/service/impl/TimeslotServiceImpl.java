package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.UserConstants;
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
            throw new NotFoundException(UserConstants.DATA_DOES_NOT_EXIST);
        }
    }

    @Override
    public TimeslotDto getTimeslotById(int id) {
        Timeslot timeslot = timeslotRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(UserConstants.TIMESLOT_NOT_FOUND));
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
            throw new NotFoundException(UserConstants.DATA_IS_EMPTY);
        }
    }

    @Override
    public String deleteTimeslot(int id) {
        if (timeslotRepo.existsById(id)) {
            timeslotRepo.deleteById(id);
            return UserConstants.DELETED_SUCCESSFULLY;
        }
        else {
            throw new NotFoundException(UserConstants.FEEDBACK_NOT_FOUND);
        }
    }

    @Override
    public boolean isTimeslotAvailable(int id) {
        Timeslot  timeslot = timeslotRepo.findById(id).orElseThrow(() -> new NotFoundException(UserConstants.ID_NOT_FOUND));

        return timeslot != null;

    }
}

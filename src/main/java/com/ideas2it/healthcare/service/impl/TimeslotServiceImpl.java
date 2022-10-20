package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.UserConstants;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.TimeslotMapper;
import com.ideas2it.healthcare.model.Timeslot;
import com.ideas2it.healthcare.repo.TimeslotRepo;
import com.ideas2it.healthcare.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
            throw new NotFoundException(UserConstants.DATA_DOES_NOT_EXIST);
        }
        return timeslotDtoToReturn;
    }

    public TimeslotDto getTimeslotById(int id) {
        Timeslot timeslot = timeslotRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(UserConstants.TIMESLOT_NOT_FOUND));
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
            throw new NotFoundException(UserConstants.DATA_IS_EMPTY);
        }
        return timeslotsDto;
    }

    public String deleteTimeslot(int id) {
        if (timeslotRepo.existsById(id)) {
            timeslotRepo.deleteById(id);
            return UserConstants.DELETED_SUCCESSFULLY;
        }
        else {
            throw new NotFoundException(UserConstants.FEEDBACK_NOT_FOUND);
        }
        //return "Deleted Successfully";
    }

    public boolean isTimeslotAvailable(int id) {
        Timeslot  timeslot = timeslotRepo.findById(id).orElseThrow(() -> new NotFoundException(UserConstants.ID_NOT_FOUND));

        return timeslot != null;

    }
}

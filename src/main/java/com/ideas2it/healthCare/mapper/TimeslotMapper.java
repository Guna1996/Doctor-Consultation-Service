package com.ideas2it.healthCare.mapper;

import com.ideas2it.healthCare.dto.TimeslotDto;
import com.ideas2it.healthCare.model.Timeslot;

public class TimeslotMapper {

    public static Timeslot fromDto(TimeslotDto timeslotDto) {
        Timeslot timeslot = new Timeslot();
        if(null != timeslotDto) {
            timeslot.setId(timeslotDto.getId());
            timeslot.setTimeslot(timeslotDto.getTimeslot());
        }
        return timeslot;
    }

    public static TimeslotDto toDto(Timeslot timeslot) {
        TimeslotDto timeslotDto = new TimeslotDto();
        if(null != timeslot) {
            timeslotDto.setId(timeslot.getId());
            timeslotDto.setTimeslot(timeslot.getTimeslot());
        }
        return timeslotDto;
    }
}

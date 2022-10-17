package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.dto.TimeslotDto;
import com.ideas2it.healthCare.service.TimeslotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/timeslot")
public class TimeslotController {

    @Autowired
    private TimeslotService timeslotService;

    @GetMapping
    public ResponseEntity<List<TimeslotDto>> getAllTimeslots() {
        return new ResponseEntity<>(timeslotService.getTimeslots(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TimeslotDto> updateTimeslot(@RequestBody TimeslotDto timeslotDto) {
        return new ResponseEntity<>(timeslotService.updateTimeslot(timeslotDto),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TimeslotDto> getTimeslotById(@PathVariable("id") int id) {
        return new ResponseEntity<>(timeslotService.getTimeslotById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TimeslotDto> insertFeedback(@RequestBody TimeslotDto timeslotDto) {
        return new ResponseEntity<>(timeslotService.addTimeslot(timeslotDto),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTimeslotById(@PathVariable("id") int id) {
        return new ResponseEntity<>(timeslotService.deleteTimeslot(id), HttpStatus.OK);
    }

}

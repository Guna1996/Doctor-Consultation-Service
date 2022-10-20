package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.service.TimeslotService;
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

    @GetMapping("/{pageNumber}/{totalRows}")
    public ResponseEntity<List<TimeslotDto>> getAllTimeslots(@PathVariable("pageNumber") int pageNumber
            , @PathVariable("totalRows") int totalRows) {
        return new ResponseEntity<>(timeslotService.getTimeslots(pageNumber, totalRows), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TimeslotDto> updateTimeslot(@RequestBody TimeslotDto timeslotDto) {
        return new ResponseEntity<>(timeslotService.updateTimeslot(timeslotDto),HttpStatus.OK);
    }

    @GetMapping(Constants.ID)
    public ResponseEntity<TimeslotDto> getTimeslotById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(timeslotService.getTimeslotById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TimeslotDto> insertFeedback(@RequestBody TimeslotDto timeslotDto) {
        return new ResponseEntity<>(timeslotService.addTimeslot(timeslotDto),HttpStatus.OK);
    }


    @DeleteMapping(Constants.ID)
    public ResponseEntity<String> deleteTimeslotById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(timeslotService.deleteTimeslot(id), HttpStatus.OK);
    }

}

package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.dto.AppointmentDto;
import com.ideas2it.healthCare.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    /**
     * Create new Transaction
     *
     * @param appointmentDto is appointment object
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<AppointmentDto> add(@Valid @RequestBody AppointmentDto appointmentDto) {
        return new ResponseEntity<>(appointmentService.addAppointment(appointmentDto), HttpStatus.OK);
    }
}

package com.ideas2it.healthCare.controller;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.FeedbackDto;
import com.ideas2it.healthCare.dto.VitalsDto;
import com.ideas2it.healthCare.service.VitalsService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/vitals")
public class VitalsController {

    private final VitalsService vitalsService;

    @GetMapping
    public ResponseEntity<List<VitalsDto>> getAllVitals() {
        return new ResponseEntity<>(vitalsService.getVitals(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<VitalsDto> updateVitals(@RequestBody VitalsDto vitalsDto) {
        return new ResponseEntity<>(vitalsService.updateVitals(vitalsDto),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VitalsDto> getVitalById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(vitalsService.getVitalsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VitalsDto> insertFeedback(@RequestBody VitalsDto vitalsDto) {
        System.out.println("yes it's coming");
        vitalsDto.setStatus(Constants.ACTIVE);
        return new ResponseEntity<>(vitalsService.addVitals(vitalsDto),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteFeedbackById(@PathVariable(Constants.PATH_ID) int id) {
        return new ResponseEntity<>(vitalsService.deleteVitals(id), HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<VitalsDto>> getVitalsByPatientId(@PathVariable(Constants.PATH_PATIENTID) int patientId) {
        return new ResponseEntity<>(vitalsService.getVitalsByPatientId(patientId), HttpStatus.OK);
    }
}

package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.dto.VitalsDto;
import com.ideas2it.healthcare.service.VitalsService;
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
@RequestMapping(value = "/vital")
public class VitalsController {

    private final VitalsService vitalsService;

    @GetMapping("/{pageNumber}/{totalRows}")
    public ResponseEntity<List<VitalsDto>> getAllVitals(@PathVariable("pageNumber") int pageNumber,
                                                        @PathVariable("totalRows") int totalRows) {
        return new ResponseEntity<>(vitalsService.getVitals(pageNumber, totalRows), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<VitalsDto> updateVitals(@RequestBody VitalsDto vitalsDto) {
        return new ResponseEntity<>(vitalsService.updateVitals(vitalsDto),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalsDto> getVitalById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vitalsService.getVitalsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VitalsDto> insertFeedback(@RequestBody VitalsDto vitalsDto) {
        vitalsDto.setStatus(Constants.ACTIVE);
        return new ResponseEntity<>(vitalsService.addVitals(vitalsDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedbackById(@PathVariable("id") int id) {
        return new ResponseEntity<>(vitalsService.deleteVitals(id), HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}/{pageNumber}/{totalRows}")
    public ResponseEntity<List<VitalsDto>> getVitalsByPatientId(@PathVariable("patientId") int patientId,
                                                                @PathVariable("pageNumber") int pageNumber,
                                                                @PathVariable("totalRows") int totalRows) {
        return new ResponseEntity<>(vitalsService
                .getVitalsByPatientId(patientId, pageNumber, totalRows), HttpStatus.OK);
    }
}

package com.ideas2it.doctorConsultancyService.controller;

import com.ideas2it.doctorConsultancyService.dto.ClinicDto;
import com.ideas2it.doctorConsultancyService.exception.NotFoundException;
import com.ideas2it.doctorConsultancyService.service.ClinicService;
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

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/clinic")
@RestController
public class ClinicController {
    @Autowired
    private ClinicService clinicService;

    @PostMapping
    public ResponseEntity<ClinicDto> add(@Valid @RequestBody ClinicDto clinicDto) {
        return new ResponseEntity<>(clinicService.addClinic(clinicDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClinicDto>> getClinics() throws NotFoundException {

        return new ResponseEntity<>(clinicService.getClinics(), HttpStatus.OK);
    }

    @GetMapping("/{id}")

    public ResponseEntity<ClinicDto> getById(@PathVariable("id") int id) throws NotFoundException {

        return new ResponseEntity<>(clinicService.getClinicById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClinicDto> update(@Valid @RequestBody ClinicDto clinicDto) throws NotFoundException{

        return new ResponseEntity<>(clinicService.updateClinic(clinicDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws NotFoundException{
        clinicService.deleteClinicById(id);
        return "Deleted";
    }
}

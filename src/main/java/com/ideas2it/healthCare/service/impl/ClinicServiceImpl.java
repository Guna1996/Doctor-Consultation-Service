package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.ClinicDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.model.Clinic;
import com.ideas2it.healthCare.repo.ClinicRepository;
import com.ideas2it.healthCare.service.ClinicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ClinicDto addClinic(ClinicDto clinicDto) {

        Clinic clinic = modelMapper.map(clinicDto, Clinic.class);
        return modelMapper.map(clinicRepository.save(clinic), ClinicDto.class);
    }

    public List<ClinicDto> getClinics() {

        List<Clinic> clinics = clinicRepository.findAllByStatus(Constants.ACTIVE);

        if (clinics.isEmpty()) {
            throw new NotFoundException("No clinic Found");
        }
        return clinics.stream()
                .map(clinic -> modelMapper.map(clinic, ClinicDto.class))
                .collect(Collectors.toList());

    }

    public ClinicDto getClinicById(int id) {

        return clinicRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                map(clinic -> modelMapper.map(clinic, ClinicDto.class)).
                findFirst().
                orElseThrow(() -> new NotFoundException("NO clinic Found"));
    }

    public ClinicDto updateClinic(ClinicDto clinicDto) {

        Optional<Clinic> clinicById = clinicRepository.findByIdAndStatus(clinicDto.getId(), Constants.ACTIVE);
        if (clinicById.isEmpty()) {
            throw new NotFoundException("No Clinic Found");
        }
        return modelMapper.map(clinicRepository.save(modelMapper.map(clinicDto, Clinic.class)), ClinicDto.class);
    }

    public String deleteClinicById(int id) {

        Optional<Clinic> clinic = clinicRepository.findByIdAndStatus(id, Constants.ACTIVE);
        if (clinic.isEmpty()) {
            throw new NotFoundException("No Clinic Found");
        }
        Clinic deletedClinic = clinic.get();
        deletedClinic.setStatus(Constants.INACTIVE);
        clinicRepository.save(deletedClinic);
        return "deleted successfully";
    }

    public boolean isClinicAvailable(int id) {

        Optional<Clinic> clinic = clinicRepository.findByIdAndStatus(id, Constants.ACTIVE);
        return clinic.isPresent();
    }
}

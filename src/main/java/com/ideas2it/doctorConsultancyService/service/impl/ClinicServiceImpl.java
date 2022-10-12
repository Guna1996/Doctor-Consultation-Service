package com.ideas2it.doctorConsultancyService.service.impl;

import com.ideas2it.doctorConsultancyService.common.Constants;
import com.ideas2it.doctorConsultancyService.dto.ClinicDto;
import com.ideas2it.doctorConsultancyService.exception.NotFoundException;
import com.ideas2it.doctorConsultancyService.mapper.ClinicMapper;
import com.ideas2it.doctorConsultancyService.model.Clinic;
import com.ideas2it.doctorConsultancyService.repo.ClinicRepository;
import com.ideas2it.doctorConsultancyService.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public ClinicDto addClinic(ClinicDto clinicDto) {
        Clinic clinic = ClinicMapper.fromDto(clinicDto);
        return ClinicMapper.toDto(clinicRepository.save(clinic));
    }

    public List<ClinicDto> getClinics() throws NotFoundException {

        List<Clinic> clinics = (List<Clinic>) clinicRepository.findAll();

        if (clinics.isEmpty()) {
            throw new NotFoundException("No clinic Found");
        } else {
            return clinics.stream()
                    .map(ClinicMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    public ClinicDto getClinicById(int id) throws NotFoundException {
        return clinicRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                map(ClinicMapper::toDto).
                findFirst().
                orElseThrow(() -> new NotFoundException("NO clinic Found"));
    }

    public ClinicDto updateClinic(ClinicDto clinicDto) throws NotFoundException {
        Optional<Clinic> clinicById = clinicRepository.findByIdAndStatus(clinicDto.getId(), Constants.ACTIVE);
        if (clinicById.isPresent()) {
            Clinic clinic = ClinicMapper.fromDto(clinicDto);
            return ClinicMapper.toDto(clinicRepository.save(clinic));
        } else {
            throw new NotFoundException("No Clinic Found");
        }
    }

    public String deleteClinicById(int id) throws NotFoundException{
        Optional<Clinic> clinicById = clinicRepository.findByIdAndStatus(id, Constants.ACTIVE);
        if (clinicById.isPresent()) {
            Clinic clinic = clinicById.get();
            clinic.setStatus(Constants.INACTIVE);
            clinicRepository.save(clinic);
            return "deleted successfully";
        } else {
            throw new NotFoundException("No Clinic Found");
        }
    }
}

package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.VitalsDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.VitalsMapper;
import com.ideas2it.healthCare.model.Vitals;
import com.ideas2it.healthCare.repo.VitalsRepo;
import com.ideas2it.healthCare.service.VitalsService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VitalsServiceImpl implements VitalsService {

    private final VitalsRepo vitalsRepo;

    public VitalsServiceImpl(VitalsRepo vitalsRepo) {
        this.vitalsRepo = vitalsRepo;
    }

    public VitalsDto addVitals(VitalsDto vitalsDto) {
        return VitalsMapper.toDto(vitalsRepo.save(VitalsMapper.fromDto(vitalsDto)));
    }

    public VitalsDto updateVitals(VitalsDto vitalsDto) {
        VitalsDto vitalsDtoToReturn = null;
        if (vitalsRepo.existsByIdAndStatus(vitalsDto.getId(), vitalsDto.getStatus())) {
            vitalsDtoToReturn =  VitalsMapper.toDto(vitalsRepo.save(VitalsMapper.fromDto(vitalsDto)));
        }
        else {
            throw new NotFoundException("The data doesn't exist");
        }
        return vitalsDtoToReturn;
    }

    public VitalsDto getVitalsById(int id) {
        Vitals vitals = vitalsRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("feedback not found"));
        if (vitals.getDiastolic() < 80 && vitals.getSystolic() < 120) {
            vitals.setBloodPressure("Normal");
        } else if (vitals.getSystolic() > 120 || vitals.getDiastolic() > 80) {
            vitals.setBloodPressure("High");
        }
        else {
            vitals.setBloodPressure("Low");
        }
        return VitalsMapper.toDto(vitals);
    }

    public List<VitalsDto> getVitals(int pageNumber, int totalRows) {
        List<VitalsDto> vitalsDto = null;
        List<Vitals> vitals = vitalsRepo.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (!vitals.isEmpty()) {
            vitalsDto = new ArrayList<>();
            for (Vitals vital : vitals) {
                if (vital.getDiastolic() < 80 && vital.getSystolic() < 120) {
                    vital.setBloodPressure("normal");
                } else if (vital.getSystolic() > 120 || vital.getDiastolic() > 80) {
                    vital.setBloodPressure("High");
                }
                else {
                    vital.setBloodPressure("Low");
                }
                vitalsDto.add(VitalsMapper.toDto(vital));
            }
        }
        else {
            throw new NotFoundException("Data is empty");
        }
        return vitalsDto;
    }

    public String deleteVitals(int id) {
        Vitals vitals = vitalsRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Feedback not found"));
        vitals.setStatus(Constants.INACTIVE);
        vitalsRepo.save(vitals);
        return "Deleted Successfully";
    }

    public List<VitalsDto> getVitalsByPatientId(int patientId, int pageNumber, int totalRows) {
        List<VitalsDto> vitalsDto = null;
        List<Vitals> vitals = vitalsRepo.findByPatientId(patientId,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (!(vitals.isEmpty())) {
            vitalsDto = vitals.stream()
                    .map(VitalsMapper::toDto).collect(Collectors.toList());
        }
        else {
            throw new NotFoundException("Vitals not found for patient");
        }
        return vitalsDto;
    }
}

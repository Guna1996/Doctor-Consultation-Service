package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.VitalsDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.model.Vitals;
import com.ideas2it.healthCare.repo.VitalsRepo;
import com.ideas2it.healthCare.service.VitalsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springfox.documentation.schema.ModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VitalsServiceImpl implements VitalsService {

    private final VitalsRepo vitalsRepo;

    private final ModelMapper modelMapper;

    @Override
    public VitalsDto addVitals(VitalsDto vitalsDto) {
        return modelMapper.map(vitalsRepo.save(modelMapper.map(vitalsDto, Vitals.class)), VitalsDto.class);
    }

    @Override
    public VitalsDto updateVitals(VitalsDto vitalsDto) {
        if (vitalsRepo.existsByIdAndStatus(vitalsDto.getId(), vitalsDto.getStatus())) {
            return modelMapper.map(vitalsRepo.save(modelMapper.map(vitalsDto, Vitals.class)),VitalsDto.class);
        }
        else {
            throw new NotFoundException("The data doesn't exist");
        }
    }

    @Override
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
        return modelMapper.map(vitals, VitalsDto.class);
    }

    @Override
    public List<VitalsDto> getVitals() {
        List<Vitals> vitals = vitalsRepo.findAllByStatus(Constants.ACTIVE);
        if (!vitals.isEmpty()) {
            List<VitalsDto> vitalsDto = new ArrayList<>();
            for (Vitals vital : vitals) {
                if (vital.getDiastolic() < 80 && vital.getSystolic() < 120) {
                    vital.setBloodPressure("normal");
                } else if (vital.getSystolic() > 120 || vital.getDiastolic() > 80) {
                    vital.setBloodPressure("High");
                }
                else {
                    vital.setBloodPressure("Low");
                }
                vitalsDto.add(modelMapper.map(vital, VitalsDto.class));
            }
            return vitalsDto;
        }
        else {
            throw new NotFoundException("Data is empty");
        }
    }

    @Override
    public String deleteVitals(int id) {
        Vitals vitals = vitalsRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Feedback not found"));
        vitals.setStatus(Constants.INACTIVE);
        vitalsRepo.save(vitals);
        return "Deleted Successfully";
    }

    @Override
    public List<VitalsDto> getVitalsByPatientId(int patientId) {
        List<VitalsDto> vitalsDto = null;
        List<Vitals> vitals = vitalsRepo.findByPatientId(patientId);
        if (!(vitals.isEmpty())) {
            vitalsDto = vitals.stream()
                    .map(vital -> modelMapper.map(vitals, VitalsDto.class)).toList();
        }
        else {
            throw new NotFoundException("Vitals not found for patient");
        }
        return vitalsDto;
    }
}

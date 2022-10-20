package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.common.UserConstants;
import com.ideas2it.healthCare.dto.VitalsDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.VitalsMapper;
import com.ideas2it.healthCare.model.Vitals;
import com.ideas2it.healthCare.repo.VitalsRepo;
import com.ideas2it.healthCare.service.VitalsService;
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

    @Override
    public VitalsDto addVitals(VitalsDto vitalsDto) {
        return VitalsMapper.toDto(vitalsRepo.save(VitalsMapper.fromDto(vitalsDto)));
    }

    @Override
    public VitalsDto updateVitals(VitalsDto vitalsDto) {
        if (vitalsRepo.existsByIdAndStatus(vitalsDto.getId(), vitalsDto.getStatus())) {
            return VitalsMapper.toDto(vitalsRepo.save(VitalsMapper.fromDto(vitalsDto)));
        }
        else {
            throw new NotFoundException(UserConstants.DATA_DOES_NOT_EXIST);
        }
    }

    @Override
    public VitalsDto getVitalsById(int id) {
        Vitals vitals = vitalsRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(UserConstants.FEEDBACK_NOT_FOUND));
        if (vitals.getDiastolic() < 80 && vitals.getSystolic() < 120) {
            vitals.setBloodPressure(UserConstants.NORMAL);
        } else if (vitals.getSystolic() > 120 || vitals.getDiastolic() > 80) {
            vitals.setBloodPressure(UserConstants.HIGH);
        }
        else {
            vitals.setBloodPressure(UserConstants.LOW);
        }
        return VitalsMapper.toDto(vitals);
    }

    @Override
    public List<VitalsDto> getVitals() {
        List<Vitals> vitals = vitalsRepo.findAllByStatus(Constants.ACTIVE);
        if (!vitals.isEmpty()) {
            List<VitalsDto> vitalsDto = new ArrayList<>();
            for (Vitals vital : vitals) {
                if (vital.getDiastolic() < 80 && vital.getSystolic() < 120) {
                    vital.setBloodPressure(UserConstants.NORMAL);
                } else if (vital.getSystolic() > 120 || vital.getDiastolic() > 80) {
                    vital.setBloodPressure(UserConstants.HIGH);
                }
                else {
                    vital.setBloodPressure(UserConstants.LOW);
                }
                vitalsDto.add(VitalsMapper.toDto(vital));
            }
            return vitalsDto;
        }
        else {
            throw new NotFoundException(UserConstants.DATA_IS_EMPTY);
        }
    }

    @Override
    public String deleteVitals(int id) {
        Vitals vitals = vitalsRepo.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(UserConstants.FEEDBACK_NOT_FOUND));
        vitals.setStatus(Constants.INACTIVE);
        vitalsRepo.save(vitals);
        return UserConstants.DELETED_SUCCESSFULLY;
    }

    @Override
    public List<VitalsDto> getVitalsByPatientId(int patientId) {
        List<VitalsDto> vitalsDto = null;
        List<Vitals> vitals = vitalsRepo.findByPatientId(patientId);
        if (!(vitals.isEmpty())) {
            vitalsDto = vitals.stream()
                    .map(VitalsMapper::toDto).collect(Collectors.toList());
        }
        else {
            throw new NotFoundException(UserConstants.VITALS_NOT_FOUND_FOR_PATIENT);
        }
        return vitalsDto;
    }
}

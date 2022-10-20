package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.UserConstants;
import com.ideas2it.healthcare.dto.VitalsDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.VitalsMapper;
import com.ideas2it.healthcare.model.Vitals;
import com.ideas2it.healthcare.repo.VitalsRepo;
import com.ideas2it.healthcare.service.VitalsService;
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
            throw new NotFoundException(UserConstants.DATA_DOES_NOT_EXIST);
        }
        return vitalsDtoToReturn;
    }

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

    public List<VitalsDto> getVitals(int pageNumber, int totalRows) {
        List<VitalsDto> vitalsDto = null;
        List<Vitals> vitals = vitalsRepo.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (!vitals.isEmpty()) {
            vitalsDto = new ArrayList<>();
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
        }
        else {
            throw new NotFoundException(UserConstants.DATA_IS_EMPTY);
        }
        return vitalsDto;
    }

    public String deleteVitals(int id) {
        if (vitalsRepo.deleteVitalsById(id) == 1){
            return "Deleted Successfully";
        }
        return "Doctor is not Deleted";
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
            throw new NotFoundException(UserConstants.VITALS_NOT_FOUND_FOR_PATIENT);
        }
        return vitalsDto;
    }
}

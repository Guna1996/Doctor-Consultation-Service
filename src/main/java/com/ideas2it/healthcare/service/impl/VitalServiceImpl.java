/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * </p>
 * <p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.UserConstants;
import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.VitalsMapper;
import com.ideas2it.healthcare.model.Vital;
import com.ideas2it.healthcare.repo.VitalsRepository;
import com.ideas2it.healthcare.service.VitalService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * This VitalServiceImpl class is a service class this class implements
 * VitalService which is an interface and get information from
 * the repository
 * </p>
 *
 * @author Bala Ashwanth
 *
 * @since 2022-10-10
 */
@Service
public class VitalServiceImpl implements VitalService {

    private final VitalsRepository vitalsRepository;

    public VitalServiceImpl(VitalsRepository vitalsRepository) {
        this.vitalsRepository = vitalsRepository;
    }

    /**
     * {@inheritDoc}
     */
    public VitalDto addVitals(VitalDto vitalsDto) {
        return VitalsMapper.toDto(vitalsRepository.save(VitalsMapper.fromDto(vitalsDto)));
    }

    /**
     * {@inheritDoc}
     */
    public VitalDto updateVitals(VitalDto vitalsDto) {
        VitalDto vitalsDtoToReturn = null;
        if (vitalsRepository.existsByIdAndStatus(vitalsDto.getId(), vitalsDto.getStatus())) {
            vitalsDtoToReturn = VitalsMapper.toDto(vitalsRepository.save(VitalsMapper.fromDto(vitalsDto)));
        } else {
            throw new NotFoundException(UserConstants.DATA_DOES_NOT_EXIST);
        }
        return vitalsDtoToReturn;
    }

    /**
     * {@inheritDoc}
     */
    public VitalDto getVitalsById(int id) {
        Vital vitals = vitalsRepository.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(UserConstants.FEEDBACK_NOT_FOUND));
        if (vitals.getDiastolic() < 80 && vitals.getSystolic() < 120) {
            vitals.setBloodPressure(UserConstants.NORMAL);
        } else if (vitals.getSystolic() > 120 || vitals.getDiastolic() > 80) {
            vitals.setBloodPressure(UserConstants.HIGH);
        } else {
            vitals.setBloodPressure(UserConstants.LOW);
        }
        return VitalsMapper.toDto(vitals);
    }

    /**
     * {@inheritDoc}
     */
    public List<VitalDto> getVitals(int pageNumber, int totalRows) {
        List<VitalDto> vitalsDto = null;
        List<Vital> vitals = vitalsRepository.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (!vitals.isEmpty()) {
            vitalsDto = new ArrayList<>();
            for (Vital vital : vitals) {
                if (vital.getDiastolic() < 80 && vital.getSystolic() < 120) {
                    vital.setBloodPressure(UserConstants.NORMAL);
                } else if (vital.getSystolic() > 120 || vital.getDiastolic() > 80) {
                    vital.setBloodPressure(UserConstants.HIGH);
                } else {
                    vital.setBloodPressure(UserConstants.LOW);
                }
                vitalsDto.add(VitalsMapper.toDto(vital));
            }
        } else {
            throw new NotFoundException(UserConstants.DATA_IS_EMPTY);
        }
        return vitalsDto;
    }

    /**
     * {@inheritDoc}
     */
    public String deleteVitals(int id) {
        if (vitalsRepository.deleteVitalsById(id) == 1) {
            return "Deleted Successfully";
        }
        return ErrorConstants.Vital_NOT_FOUND;
    }

    /**
     * {@inheritDoc}
     */
    public List<VitalDto> getVitalsByPatientId(int patientId, int pageNumber, int totalRows) {
        List<VitalDto> vitalDto = null;
        List<Vital> vitals = vitalsRepository.findByPatientId(patientId,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (vitals.isEmpty()) {
            throw new NotFoundException(ErrorConstants.Vital_NOT_FOUND);
        }
        vitalDto = vitals.stream()
                .map(VitalsMapper::toDto).collect(Collectors.toList());
        return vitalDto;
    }
}

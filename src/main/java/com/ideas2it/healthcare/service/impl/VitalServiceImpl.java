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
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.VitalDto;
import com.ideas2it.healthcare.mapper.VitalsMapper;
import com.ideas2it.healthcare.repo.VitalsRepository;
import com.ideas2it.healthcare.service.VitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
 * @since 2022-10-10
 */
@Service
public class VitalServiceImpl implements VitalService {

    @Autowired
    private VitalsRepository vitalsRepository;

    /**
     * {@inheritDoc}
     */
    public VitalDto addVitals(VitalDto vitalsDto) {
        if (vitalsDto.getDiastolic() <= 80 && vitalsDto.getSystolic() <= 120) {
            vitalsDto.setBPRiskLevel(MessageConstants.NORMAL);
        } else if (vitalsDto.getSystolic() > 120 || vitalsDto.getDiastolic() > 80) {
            vitalsDto.setBPRiskLevel(MessageConstants.HIGH);
        } else {
            vitalsDto.setBPRiskLevel(MessageConstants.LOW);
        }
        return VitalsMapper.toDto(vitalsRepository.save(VitalsMapper.fromDto(vitalsDto)));
    }

    /**
     * {@inheritDoc}
     */
    public List<VitalDto> getVitalsByPatientId(int patientId, int pageNumber, int totalRows) {
        return vitalsRepository
                .findByPatientIdAndStatus(patientId, Constants.ACTIVE, PageRequest.of(pageNumber, totalRows))
                .toList().stream()
                .map(VitalsMapper::toDto).collect(Collectors.toList());
    }
}

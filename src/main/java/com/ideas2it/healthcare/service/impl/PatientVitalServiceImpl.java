/**
 * <p>
 * This package contains classes are Doctor Clinic Impl,
 * Patient Impl, Doctor Impl, Clinic Impl,
 * Appointment Impl, Feedback Impl, Specialization Impl,
 * Timeslot Impl, Patient Vital Impl
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.PatientVitalDto;
import com.ideas2it.healthcare.mapper.PatientVitalMapper;
import com.ideas2it.healthcare.repository.PatientVitalRepository;
import com.ideas2it.healthcare.service.PatientVitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * This Patient Vital Service Impl class is a service class this class implements
 * Patient Vital Service which is an interface and get information from
 * the repository
 * </p>
 *
 * @author Bala Ashwanth
 * @since 2022-10-10
 */
@Service
public class PatientVitalServiceImpl implements PatientVitalService {

    @Autowired
    private PatientVitalRepository patientVitalsRepository;

    /**
     * {@inheritDoc}
     */
    public String addVitals(PatientVitalDto vitalsDto) {
        patientVitalsRepository.save(PatientVitalMapper.fromDto(vitalsDto));
        return MessageConstants.VITALS_ADDED_SUCCESSFULLY;
    }

    /**
     * {@inheritDoc}
     */
    public List<PatientVitalDto> getVitalsByPatientId(
            Integer patientId, Integer pageNumber, Integer totalRows) {
        return patientVitalsRepository
                .findByPatientIdAndStatus(patientId, Constants.ACTIVE, PageRequest.of(pageNumber,
                        totalRows))
                .toList().stream()
                .map(PatientVitalMapper::toDto).collect(Collectors.toList());

    }

    /**
     * {@inheritDoc}
     */
    public Integer getVitalsCountByPatientId(Integer patientId) {
        return patientVitalsRepository.countByPatientIdAndStatus(patientId, Constants.ACTIVE);
    }
}

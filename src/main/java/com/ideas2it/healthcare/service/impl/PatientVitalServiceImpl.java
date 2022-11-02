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
import com.ideas2it.healthcare.dto.PatientVitalDto;
import com.ideas2it.healthcare.mapper.PatientVitalMapper;
import com.ideas2it.healthcare.repository.VitalsRepository;
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
public class PatientVitalServiceImpl implements VitalService {

    private Long totalPages;
    @Autowired
    private VitalsRepository vitalsRepository;

    /**
     * {@inheritDoc}
     */
    public PatientVitalDto addVitals(PatientVitalDto vitalsDto) {
        return PatientVitalMapper.toDto(vitalsRepository.save(PatientVitalMapper.fromDto(vitalsDto)));
    }

    /**
     * {@inheritDoc}
     */
    public List<PatientVitalDto> getVitalsByPatientId(Integer patientId, Integer pageNumber, Integer totalRows) {
        setTotalPages(Math.round(((vitalsRepository
                .findByPatientIdAndStatus(patientId, Constants.ACTIVE).size() + 0.0) / totalRows) + 0.4));
        return vitalsRepository
                .findByPatientIdAndStatus(patientId, Constants.ACTIVE, PageRequest.of(pageNumber,
                        totalRows))
                .toList().stream()
                .map(PatientVitalMapper::toDto).collect(Collectors.toList());
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }
}

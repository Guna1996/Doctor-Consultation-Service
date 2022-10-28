/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.dto.ClinicDto;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.ClinicMapper;
import com.ideas2it.healthcare.model.Clinic;
import com.ideas2it.healthcare.repo.ClinicRepository;
import com.ideas2it.healthcare.service.ClinicService;
import com.ideas2it.healthcare.service.DoctorClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Clinic serviceImpl class implements Clinic service
 * and it contains methods and with helps of passing object to
 * ClinicRepository interface
 * </p>
 *
 * @author Gunaseelan K
 * @version 1
 * @since 2022-10-10
 */
@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private DoctorClinicService doctorClinicService;

    /**
     * {@inheritDoc}
     */
    public ClinicDto addClinic(ClinicDto clinicDto) {
        Clinic clinic = ClinicMapper.fromDto(clinicDto);
        return ClinicMapper.toDto(clinicRepository.save(clinic));
    }

    /**
     * {@inheritDoc}
     */
    public List<ClinicDto> getClinics(int pageNumber, int totalRows) {
        List<Clinic> clinics = clinicRepository.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();

        if (clinics.isEmpty()) {
            throw new NotFoundException(ErrorConstants.CLINIC_NOT_FOUND);
        }
        return clinics.stream()
                .map(ClinicMapper::toDto)
                .collect(Collectors.toList());

    }

    /**
     * {@inheritDoc}
     */
    public ClinicDto getClinicById(int id) {
        return clinicRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                map(ClinicMapper::toDto).
                findFirst().
                orElseThrow(() -> new NotFoundException(ErrorConstants.CLINIC_NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */
    public ClinicDto updateClinic(ClinicDto clinicDto) {
        return ClinicMapper.toDto(clinicRepository.save(ClinicMapper.fromDto(clinicDto)));
    }

    /**
     * {@inheritDoc}
     */
    public String deleteClinicById(int id) {
        if (clinicRepository.deleteClinicById(id) == 1) {
            return ErrorConstants.CLINIC_DELETED_SUCCESSFULLY;
        }
        return ErrorConstants.CLINIC_NOT_FOUND;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isClinicAvailable(int id) {
        return clinicRepository.existsByIdAndStatus(id, Constants.ACTIVE);
    }

    /**
     * {@inheritDoc}
     */
    public List<DoctorClinicDto> getDoctorsByClinicId(int clinicId, int pageNumber, int totalRows) {
        return doctorClinicService.getDoctorsByClinicId(clinicId, pageNumber, totalRows);
    }
}


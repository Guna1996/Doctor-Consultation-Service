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
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.DoctorClinicMapper;
import com.ideas2it.healthcare.model.DoctorClinic;
import com.ideas2it.healthcare.repo.DoctorClinicRepository;
import com.ideas2it.healthcare.service.DoctorClinicService;
import com.ideas2it.healthcare.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * DoctorClinicServiceImpl class implements DoctorClinicService
 * and it contains methods and with helps of passing object to
 * DoctorClinicRepository interface
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@Service
public class DoctorClinicServiceImpl implements DoctorClinicService {

    @Autowired
    private DoctorClinicRepository doctorClinicRepository;

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto assignDoctorToClinic(DoctorClinicDto doctorClinicDto) {
        return DoctorClinicMapper.toDto(DoctorClinicMapper.fromDto(doctorClinicDto));
    }

    /**
     * {@inheritDoc}
     */
    public String deleteDoctorFromClinic(Integer id) {
        if (doctorClinicRepository.deleteDoctorClinicById(id) == 1) {
            return ErrorConstants.SUCCESSFULLY_DELETED_DOCTOR_FROM_CLINIC;
        }
        return MessageConstants.DOCTOR_UNABLE_TO_DELETE;
    }

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto updateDoctorClinic(DoctorClinicDto doctorClinicDto) {
        if (!doctorClinicRepository.existsByIdAndStatus(doctorClinicDto.getId(), Constants.ACTIVE)) {
            throw new NotFoundException(MessageConstants.DOCTOR_UNABLE_TO_UPDATE);
        }
        return DoctorClinicMapper.toDto(doctorClinicRepository
                .save(DoctorClinicMapper.fromDto(doctorClinicDto)));
    }

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto getTimeslotsByDoctorIdAndClinicId(int doctorId, int clinicId) {
        return DoctorClinicMapper.toDto(doctorClinicRepository.findByDoctorIdAndClinicIdAndStatus(doctorId
                        , clinicId, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(MessageConstants.DOCTOR_ID_CLINIC_ID_NOT_FOUND)));
    }

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto getDoctorClinicById(int id) {
        return doctorClinicRepository.findByIdAndStatus(id, Constants.ACTIVE).stream().
                map(DoctorClinicMapper::toDto).
                findFirst().
                orElseThrow(() -> new NotFoundException(ErrorConstants.DOCTOR_OR_CLINIC_NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */
    public List<DoctorClinicDto> getDoctorsByClinicId(int clinicId, int pageNumber, int totalRows) {
        return doctorClinicRepository.findByClinicIdAndStatus(clinicId, Constants.ACTIVE
                        , PageRequest.of(pageNumber, totalRows)).toList().stream()
                .map(DoctorClinicMapper::toDto).collect(Collectors.toList());
    }
}


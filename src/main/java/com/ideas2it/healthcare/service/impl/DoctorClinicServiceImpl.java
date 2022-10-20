/**
 * <p>
 * This package contains classes are DoctorClinicImpl,
 * PatientImpl, DoctorImpl, ClinicImpl,
 * AppointmentImpl, FeedbackImpl, SpecializationImpl,
 * TimeslotImpl, VitalsImpl
 * </p>
 *
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.service.impl;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.UserConstants;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.mapper.DoctorClinicMapper;
import com.ideas2it.healthcare.model.DoctorClinic;
import com.ideas2it.healthcare.repo.DoctorClinicRepository;
import com.ideas2it.healthcare.service.ClinicService;
import com.ideas2it.healthcare.service.DoctorClinicService;
import com.ideas2it.healthcare.service.DoctorService;
import com.ideas2it.healthcare.service.TimeslotService;
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
 *
 * @version 1
 *
 * @since 2022-10-10
 */
@Service
public class DoctorClinicServiceImpl implements DoctorClinicService {

    @Autowired
    private DoctorClinicRepository doctorClinicRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private TimeslotService timeslotService;

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto assignDoctorToClinic(DoctorClinicDto doctorClinicDto) {
        DoctorClinicDto toReturnDto = null;
        if (doctorService.isDoctorAvailable(doctorClinicDto.getDoctor().getId())&&
                clinicService.isClinicAvailable(doctorClinicDto.getClinic().getId())) {
            DoctorClinic doctorClinic = DoctorClinicMapper.fromDto(doctorClinicDto);
            toReturnDto = DoctorClinicMapper.toDto(doctorClinicRepository.save(doctorClinic));
        } else {
            throw new NotFoundException(UserConstants.DOCTOR_NOT_FOUND_TO_ASSIGN);
        }
        return toReturnDto;
    }

    /**
     * {@inheritDoc}
     */
    public List<DoctorClinicDto> getDoctorClinics(int pageNumber, int totalRows) {
        List<DoctorClinic> doctorClinics = doctorClinicRepository.findAllByStatus(Constants.ACTIVE,
                PageRequest.of(pageNumber, totalRows)).toList();
        if (doctorClinics.isEmpty()) {
            throw new NotFoundException(ErrorConstants.CLINIC_NOT_FOUND);
        }
            return doctorClinics.stream()
                    .map(DoctorClinicMapper::toDto)
                    .collect(Collectors.toList());

    }

    /**
     * {@inheritDoc}
     */
    public String deleteDoctorFromClinic(Integer id) {
        if (doctorClinicRepository.deleteDoctorClinicById(id) == 1){
            return "Deleted Successfully";
        }
        return "Doctor is not Deleted";
    }

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto updateDoctorClinic(DoctorClinicDto doctorClinicDto) {
        if (doctorClinicRepository.existsByIdAndStatus(doctorClinicDto.getId(), Constants.ACTIVE)) {
            return DoctorClinicMapper.toDto(doctorClinicRepository
                    .save(DoctorClinicMapper.fromDto(doctorClinicDto)));
        }
        throw new NotFoundException(UserConstants.DOCTOR_ID_NOT_FOUND_TO_UPDATE);
    }

    @Override
    public DoctorClinicDto getByDoctorIdAndClinicId(int doctorId, int clinicId) {
        return DoctorClinicMapper.toDto(doctorClinicRepository.findByDoctorIdAndClinicIdAndStatus(doctorId, clinicId, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException(UserConstants.DOCTOR_ID_CLINICID_NOT_FOUND)));
    }
}


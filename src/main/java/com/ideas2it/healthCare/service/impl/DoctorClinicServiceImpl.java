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
package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.DoctorClinicDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.mapper.DoctorClinicMapper;
import com.ideas2it.healthCare.model.DoctorClinic;
import com.ideas2it.healthCare.repo.DoctorClinicRepository;
import com.ideas2it.healthCare.service.ClinicService;
import com.ideas2it.healthCare.service.DoctorClinicService;
import com.ideas2it.healthCare.service.DoctorService;
import com.ideas2it.healthCare.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2022-07-18
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
            throw new NotFoundException("doctor not found to assign");
        }
        return toReturnDto;
    }

    /**
     * {@inheritDoc}
     */
    public List<DoctorClinicDto> getDoctorClinics() {
        List<DoctorClinic> doctorClinics = doctorClinicRepository.findAllByStatus(Constants.ACTIVE);
        if (doctorClinics.isEmpty()) {
            throw new NotFoundException("No clinic Found");
        } else {
            return doctorClinics.stream()
                    .map(DoctorClinicMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    /**
     * {@inheritDoc}
     */
    public String deleteDoctorFromClinic(Integer id) {
        DoctorClinic doctorClinic = doctorClinicRepository.findByIdAndStatus(id, Constants.ACTIVE)
                .orElseThrow(() ->  new NotFoundException("Doctor not found to delete"));
        doctorClinic.setStatus(Constants.INACTIVE);
        doctorClinicRepository.save(doctorClinic);
        return "Deleted Successfully";
    }

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto updateDoctorClinic(DoctorClinicDto doctorClinicDto) {
        if (doctorClinicRepository.existsByIdAndStatus(doctorClinicDto.getId(), Constants.ACTIVE)) {
            return DoctorClinicMapper.toDto(doctorClinicRepository
                    .save(DoctorClinicMapper.fromDto(doctorClinicDto)));
        }
        throw new NotFoundException("Doctor id not found to update");
    }

    @Override
    public DoctorClinicDto getByDoctorIdAndClinicId(int doctorId, int clinicId) {
        return DoctorClinicMapper.toDto(doctorClinicRepository.findByDoctorIdAndClinicIdAndStatus(doctorId, clinicId, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("not found")));
    }
}


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
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.dto.TimeslotDto;
import com.ideas2it.healthcare.exception.CustomException;
import com.ideas2it.healthcare.mapper.DoctorClinicMapper;
import com.ideas2it.healthcare.model.DoctorClinic;
import com.ideas2it.healthcare.repository.DoctorClinicRepository;
import com.ideas2it.healthcare.service.DoctorClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Doctor Clinic Service Impl class implements Doctor Clinic Service
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
    public String assignDoctorToClinic(DoctorClinicDto doctorClinicDto) {
        String response = MessageConstants.DOCTOR_ASSIGNED_TO_CLINIC_SUCCESSFULLY;
        if (!isDoctorTimeslotAvailable(doctorClinicDto.getDoctor().getId(), doctorClinicDto.getTimeslots())) {
            response = ErrorConstants.DOCTOR_ALREADY_ASSIGNED_TO_SOME_OTHER_CLINIC_AT_THIS_TIMESLOT;
        } else {
            doctorClinicRepository.save(DoctorClinicMapper.fromDto(doctorClinicDto));
        }
        return response;
    }

    /**
     * <p>
     * This method is used to check whether a
     * doctor is assigned to different clinic in the
     * same timeslot and return boolean
     * </p>
     *
     * @param doctorId     {@link Integer}
     * @param timeslotsDto {@link List<TimeslotDto>}
     */
    private Boolean isDoctorTimeslotAvailable(Integer doctorId, List<TimeslotDto> timeslotsDto) {
        List<Integer> allTimeslotsOfDoctor = doctorClinicRepository.getTimeslots(doctorId);
        if (!allTimeslotsOfDoctor.isEmpty()) {
            for (TimeslotDto timeslotDto : timeslotsDto) {
                if (allTimeslotsOfDoctor.contains(timeslotDto.getId())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public String removeDoctorFromClinic(Integer id) {
        String response = ErrorConstants.DOCTOR_UNABLE_TO_REMOVE;
        if (1 <= doctorClinicRepository.removeDoctorClinicById(id)) {
            response = MessageConstants.SUCCESSFULLY_REMOVED_DOCTOR_FROM_CLINIC;
        }
        return response;
    }

    /**
     * {@inheritDoc}
     */
    public DoctorClinicDto getTimeslotsByDoctorIdAndClinicId(Integer doctorId, Integer clinicId) {
        return DoctorClinicMapper.toDto(doctorClinicRepository
                .findByDoctorIdAndClinicIdAndStatus(doctorId, clinicId, Constants.ACTIVE)
                .orElse(null));
    }

    /**
     * {@inheritDoc}
     */
    public List<DoctorClinicDto> getDoctorsByClinicId(Integer clinicId, Integer pageNumber,
                                                      Integer totalRows) {
        return doctorClinicRepository.findByClinicIdAndStatus(clinicId, Constants.ACTIVE,
                        PageRequest.of(pageNumber, totalRows)).toList().stream()
                .map(DoctorClinicMapper::toDto).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    public Integer getCountOfDoctorsByClinicId(Integer clinicId) {
        return doctorClinicRepository.countByClinicIdAndStatus(clinicId, Constants.ACTIVE);
    }

    /**
     * {@inheritDoc}
     */
    public String updateDoctorTimeslotsInThatClinic(DoctorClinicDto doctorClinicDto) {
        if (!isDoctorTimeslotAvailable(doctorClinicDto.getDoctor().getId(), doctorClinicDto.getTimeslots())) {
            return ErrorConstants.DOCTOR_ALREADY_ASSIGNED_TO_SOME_OTHER_CLINIC_AT_THIS_TIMESLOT;
        }
        DoctorClinic doctorClinic = doctorClinicRepository.findByDoctorIdAndClinicIdAndStatus(doctorClinicDto.getDoctor().getId(),
                doctorClinicDto.getClinic().getId(), Constants.ACTIVE).orElseThrow(()
                -> new CustomException(ErrorConstants.DOCTOR_IS_NOT_PRESENT_IN_THIS_CLINIC));
        doctorClinic.getTimeslots().addAll(DoctorClinicMapper.fromDto(doctorClinicDto).getTimeslots());
        doctorClinicRepository.save(doctorClinic);
        return MessageConstants.DOCTOR_ASSIGNED_TO_CLINIC_SUCCESSFULLY;
    }
}


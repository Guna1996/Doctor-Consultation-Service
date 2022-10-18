package com.ideas2it.healthCare.service.impl;

import com.ideas2it.healthCare.common.Constants;
import com.ideas2it.healthCare.dto.DoctorClinicDto;
import com.ideas2it.healthCare.exception.NotFoundException;
import com.ideas2it.healthCare.model.DoctorClinic;
import com.ideas2it.healthCare.repo.DoctorClinicRepository;
import com.ideas2it.healthCare.service.ClinicService;
import com.ideas2it.healthCare.service.DoctorClinicService;
import com.ideas2it.healthCare.service.DoctorService;
import com.ideas2it.healthCare.service.TimeslotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ModelMapper modelMapper;


    public DoctorClinicDto assignDoctorToClinic(DoctorClinicDto doctorClinicDto) {
        if (doctorService.isDoctorAvailable(doctorClinicDto.getDoctor().getId()) &&
                clinicService.isClinicAvailable(doctorClinicDto.getClinic().getId())) {
            DoctorClinic doctorClinic = modelMapper.map(doctorClinicDto, DoctorClinic.class);
            return modelMapper.map(doctorClinicRepository.save(doctorClinic), DoctorClinicDto.class);
        } else {
            throw new NotFoundException("doctor not found to assign");
        }
    }

    public List<DoctorClinicDto> getDoctorClinics() {
        List<DoctorClinic> doctorClinics = doctorClinicRepository.findAllByStatus(Constants.ACTIVE);
        if (doctorClinics.isEmpty()) {
            throw new NotFoundException("No clinic Found");
        } else {
            return doctorClinics.stream()
                    .map(doctorClinic -> modelMapper.map(doctorClinic, DoctorClinicDto.class))
                    .collect(Collectors.toList());
        }
    }

    public String deleteDoctorFromClinic(Integer id) {
        DoctorClinic doctorClinic = doctorClinicRepository.findByIdAndStatus(id, Constants.ACTIVE);
        if (doctorClinic != null) {
            doctorClinic.setStatus(Constants.INACTIVE);
            doctorClinicRepository.save(doctorClinic);
            return "deleted successfully";
        } else {
            throw new NotFoundException("Doctor not found to delete");
        }
    }

    public DoctorClinicDto updateDoctorClinic(DoctorClinicDto doctorClinicDto) {
        if (doctorClinicRepository.existsByIdAndStatus(doctorClinicDto.getId(), Constants.ACTIVE)) {
            return modelMapper.map(doctorClinicRepository
                    .save(modelMapper.map(doctorClinicDto, DoctorClinic.class)), DoctorClinicDto.class);
        }
        throw new NotFoundException("Doctor id not found to update");
    }

    @Override
    public DoctorClinicDto getByDoctorIdAndClinicId(int doctorId, int clinicId) {
        return modelMapper.map(doctorClinicRepository.findBlyDoctorIdAndClinicIdAndStatus(doctorId, clinicId, Constants.ACTIVE)
                .orElseThrow(() -> new NotFoundException("not found")), DoctorClinicDto.class);
    }
}


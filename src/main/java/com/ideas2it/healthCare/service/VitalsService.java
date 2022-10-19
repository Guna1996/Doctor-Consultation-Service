package com.ideas2it.healthCare.service;

import com.ideas2it.healthCare.dto.FeedbackDto;
import com.ideas2it.healthCare.dto.VitalsDto;

import java.util.List;

public interface VitalsService {

    VitalsDto addVitals(VitalsDto vitalsDto);

    VitalsDto updateVitals(VitalsDto vitalsDto);

    VitalsDto getVitalsById(int id);

    List<VitalsDto> getVitals(int pageNumber, int totalRows);

    String deleteVitals(int id);

    List<VitalsDto> getVitalsByPatientId(int patientId, int pageNumber, int totalRows);
}

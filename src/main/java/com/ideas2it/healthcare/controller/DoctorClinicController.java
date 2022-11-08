/**
 * <p>
 * This is the package contains classes are Doctor clinic controller,
 * Patient controller, Doctor controller, Clinic controller,
 * Appointment controller, Feedback controller, Specialization controller,
 * Timeslot controller, Patient Vital controller
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.controller;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.common.MessageConstants;
import com.ideas2it.healthcare.dto.DoctorClinicDto;
import com.ideas2it.healthcare.exception.NotFoundException;
import com.ideas2it.healthcare.response.CustomResponse;
import com.ideas2it.healthcare.service.DoctorClinicService;
import com.ideas2it.healthcare.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * Doctor Clinic Controller class is used to assign and delete
 * doctors from clinic and used to get timeslot of a doctor
 * in a clinic.
 * </p>
 *
 * @author Ramachandran
 * @version 1
 * @since 2022-10-10
 */
@RestController
@RequestMapping(Constants.URL_DOCTOR_CLINIC)
public class DoctorClinicController {

    @Autowired
    private DoctorClinicService doctorClinicService;
    
    @Autowired
    private CustomResponse customResponse;

    /**
     * <p>
     * This method is used to assign doctor to a clinic by getting details
     * such as doctor id, clinic id, timeslots, etc
     * </p>
     *
     * @param doctorClinicDto {@link DoctorClinicDto} is details of doctor clinic
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> assignDoctorToClinic(
            @Valid @RequestBody DoctorClinicDto doctorClinicDto) {
        return customResponse.responseEntity(doctorClinicService.assignDoctorToClinic(doctorClinicDto),
                null, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to update doctor to a clinic by getting details
     * such as doctor id, clinic id, timeslots, etc
     * </p>
     *
     * @param doctorClinicDto {@link DoctorClinicDto} id of the doctor object
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateDoctorToClinic(DoctorClinicDto doctorClinicDto){
        return customResponse.responseEntity(doctorClinicService.updateDoctorClinic(doctorClinicDto),
                null, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to remove doctor from a clinic
     * by getting doctor clinic id
     * </p>
     *
     * @param id {@link Integer} id of the doctor object
     * @return {@link ResponseEntity}
     */
    @PutMapping(Constants.URL_ID)
    public ResponseEntity<Map<String, Object>> removeDoctorFromClinic(
            @PathVariable(Constants.ID) Integer id) {
        return customResponse.responseEntity(doctorClinicService.removeDoctorFromClinic(id),
                null, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used get timeslot of a doctor to the clinics
     * by getting doctor id and clinic id
     * </p>
     *
     * @param doctorId {@link Integer} is id of doctor object
     * @param clinicId {@link Integer} is id of clinic object
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_TIMESLOTS)
    public ResponseEntity<Map<String, Object>> getTimeslots(
            @PathVariable(Constants.DOCTOR_ID_PATH) Integer doctorId,
            @PathVariable(Constants.CLINIC_ID_PATH) Integer clinicId) {
        DoctorClinicDto doctorClinicDto =
                doctorClinicService.getTimeslotsByDoctorIdAndClinicId(doctorId, clinicId);
        String message = MessageConstants.SUCCESSFULLY_RETRIEVED_ALL_TIMESLOTS;
        if (null == doctorClinicDto) {
            message = MessageConstants.DOCTOR_ID_CLINIC_ID_NOT_FOUND;
        }
        return customResponse.responseEntity(message, doctorClinicDto, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used get available doctors in a particular clinic by getting
     * clinic id, page number and total rows required. Based on the user input such
     * as page number and total rows total page is calculated and displayed
     * using pagination.
     * </p>
     *
     * @param clinicId   {@link Integer}
     * @param pageNumber {@link Integer} is page number
     * @param totalRows  {@link Integer} is number of row to be shown
     * @return {@link ResponseEntity}
     */
    @GetMapping(Constants.URL_GET_DOCTORS_BY_CLINIC_ID)
    public ResponseEntity<Map<String, Object>> getDoctorsByClinicId(
            @PathVariable(Constants.CLINIC_ID_PATH) Integer clinicId,
            @PathVariable(Constants.PAGE_NUMBER) Integer pageNumber,
            @PathVariable(Constants.TOTAL_ROWS) Integer totalRows) {
        int totalPages = doctorClinicService.getCountOfDoctorsByClinicId(clinicId);
        int pages = MathUtil.pageCount(totalPages, totalRows);
        String message = MessageConstants.SUCCESSFULLY_RETRIEVED_DOCTORS_IN_CLINIC;
        if (pages <= pageNumber) {
            message = ErrorConstants.DOCTORS_NOT_FOUND;
        }
        return customResponse.responseEntity(message,
                doctorClinicService.getDoctorsByClinicId(clinicId, pageNumber, totalRows),
                HttpStatus.OK, pages);
    }
}

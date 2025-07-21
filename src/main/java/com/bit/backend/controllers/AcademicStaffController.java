package com.bit.backend.controllers;


import com.bit.backend.dtos.AcademicStaffDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.AcademicStaffServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController

public class AcademicStaffController {

    private final AcademicStaffServiceI academicStaffServiceI;

    public AcademicStaffController(AcademicStaffServiceI academicStaffServiceI) {
        this.academicStaffServiceI = academicStaffServiceI;
    }

    @PostMapping("/academic-staff")
    public ResponseEntity<AcademicStaffDto> addAcademicStaff(@RequestBody AcademicStaffDto academicStaffDto) {

        try {

            AcademicStaffDto academicStaffDtoResponse = academicStaffServiceI.addAcademicStaffEntity(academicStaffDto);
            return ResponseEntity.created(URI.create("/academic-staff"+academicStaffDtoResponse.getTeacherNumber())).body(academicStaffDtoResponse);


        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/academic-staff")
    public ResponseEntity<List<AcademicStaffDto>>getData(){

        try {
            List<AcademicStaffDto> academicStaffDtiList = academicStaffServiceI.getData();
            return ResponseEntity.ok(academicStaffDtiList);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/academic-staff/{id}")

    public ResponseEntity<AcademicStaffDto> updateAcademicStaff(@PathVariable long id, @RequestBody AcademicStaffDto academicStaffDto){

        try {

            AcademicStaffDto responseAcademicStaffDto = academicStaffServiceI.updateAcademicStaff(id, academicStaffDto);
            return ResponseEntity.ok(responseAcademicStaffDto);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/academic-staff/{id}")
    public ResponseEntity<AcademicStaffDto> deleteAcademicStaff(@PathVariable long id) {

        try {
            AcademicStaffDto academicStaffDto = academicStaffServiceI.deleteAcademicStaff(id);
            return ResponseEntity.ok(academicStaffDto);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/acheck-nic")

    public ResponseEntity<Boolean> checkNICExists(@RequestParam String nic) {
        boolean exists = academicStaffServiceI.nicExists(nic);
        return ResponseEntity.ok(exists);
    }

}

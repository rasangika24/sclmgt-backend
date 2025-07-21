package com.bit.backend.controllers;

import com.bit.backend.dtos.NonacademicStaffDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.NonacademicStaffServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController


public class NonacademicStaffController {

    private final NonacademicStaffServiceI nonacademicStaffServiceI;

    public NonacademicStaffController(NonacademicStaffServiceI nonacademicStaffServiceI) {
        this.nonacademicStaffServiceI = nonacademicStaffServiceI;
    }

    @PostMapping("/none-academic-staff")
    public ResponseEntity<NonacademicStaffDto> addForm(@RequestBody NonacademicStaffDto nonacademicStaffDto) {

        try {
            NonacademicStaffDto nonacademicStaffDtoResponse = nonacademicStaffServiceI.addNonacademicStaffEntity(nonacademicStaffDto);
            return ResponseEntity.created(URI.create("/none-academic-staff"+nonacademicStaffDtoResponse.getTeacherNumber())).body(nonacademicStaffDtoResponse);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/none-academic-staff")
    public ResponseEntity<List<NonacademicStaffDto>>getData(){

        try {
            List<NonacademicStaffDto> nonacademicStaffDtiList = nonacademicStaffServiceI.getData();
            return ResponseEntity.ok(nonacademicStaffDtiList);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/none-academic-staff/{id}")

    public ResponseEntity<NonacademicStaffDto> updateNonacademicStaff(@PathVariable long id, @RequestBody NonacademicStaffDto nonacademicStaffDto){

        try {
            NonacademicStaffDto responseNonacademicStaffDto = nonacademicStaffServiceI.updateNonacademicStaff(id, nonacademicStaffDto);
            return ResponseEntity.ok(responseNonacademicStaffDto);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/none-academic-staff/{id}")
    public ResponseEntity<NonacademicStaffDto> deleteNoneacademicStaff(@PathVariable long id) {
        try {
            NonacademicStaffDto nonacademicStaffDto = nonacademicStaffServiceI.deleteNonacademicStaff(id);
            return ResponseEntity.ok(nonacademicStaffDto);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/nacheck-nic")

    public ResponseEntity<Boolean> checkNICExists(@RequestParam String nic) {
        boolean exists = nonacademicStaffServiceI.nicExists(nic);
        return ResponseEntity.ok(exists);
    }


}

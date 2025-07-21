package com.bit.backend.controllers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.GradesGenDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.GradesServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class GradesController {

    private final GradesServiceI gradesServiceI;

    public GradesController(GradesServiceI gradesServiceI) {
        this.gradesServiceI = gradesServiceI;
    }

    @PostMapping("/generateGrades")
    public ResponseEntity<List<GradesGenDto>> generateGrades(@RequestBody GradesGenDto gradesGenDto) {
        try {
            List<GradesGenDto> gradesGenDtoListResponse = gradesServiceI.generateGrades(gradesGenDto);
            return ResponseEntity.ok(gradesGenDtoListResponse);
        } catch (Exception e) {
            throw new AppException("Grades generation failed. Please try again later. " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

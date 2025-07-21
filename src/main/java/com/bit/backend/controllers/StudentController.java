package com.bit.backend.controllers;

import com.bit.backend.dtos.StudentDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.StudentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController

public class StudentController {
    private final StudentServiceI studentServiceI;

    public StudentController(StudentServiceI studentServiceI) {
        this.studentServiceI  = studentServiceI;
    }

    @PostMapping ("/student")
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {

        try {

            StudentDto studentDtoResponse = studentServiceI.addStudentEntity(studentDto);
            return ResponseEntity.created(URI.create("/student"+studentDtoResponse.getAdmissionNumber())).body(studentDtoResponse);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student")
        public ResponseEntity<List<StudentDto>> getData(){

        try {

            List<StudentDto> studentDtoList = studentServiceI.getData();
            return ResponseEntity.ok(studentDtoList);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        }

    @PutMapping("/student/{id}")
        public ResponseEntity<StudentDto> updateStudent(@PathVariable long id, @RequestBody StudentDto studentDto) {

        try {

            StudentDto responseStudentDto = studentServiceI.updateStudent(id, studentDto);
            return ResponseEntity.ok(responseStudentDto);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable long id) {

        try {

            StudentDto studentDto = studentServiceI.deleteStudent(id);
            return ResponseEntity.ok(studentDto);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/check-nic")

    public ResponseEntity<Boolean> checkNICExists(@RequestParam String nic) {
        boolean exists = studentServiceI.nicExists(nic);
        return ResponseEntity.ok(exists);
    }

}

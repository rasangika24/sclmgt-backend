package com.bit.backend.controllers;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.EmployeeServiceI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    private final EmployeeServiceI employeeServiceI;

    public EmployeeController(EmployeeServiceI employeeServiceI) {
        this.employeeServiceI = employeeServiceI;
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            EmployeeDto employeeDtoResponse = employeeServiceI.addEmployeeEntity(employeeDto);
            return ResponseEntity.created(URI.create("/employee" + employeeDtoResponse.getFullName())).body(employeeDtoResponse);
        } catch (Exception e) {
            throw new AppException("Employee registration failed. Please try again later. " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        try {
            List<EmployeeDto> employeeDtoList = employeeServiceI.getEmployee();
            return ResponseEntity.ok(employeeDtoList);
        } catch (Exception e) {
            throw new AppException("Failed to load employee records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
        try {
            EmployeeDto responseEmployeeDto = employeeServiceI.updateEmployee(id, employeeDto);
            return ResponseEntity.ok(responseEmployeeDto);
        } catch (Exception e) {
            throw new AppException("Failed to update employee records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable long id) {
        try {
            EmployeeDto responseEmployeeDto = employeeServiceI.deleteEmployee(id);
            return ResponseEntity.ok(responseEmployeeDto);
        } catch (Exception e) {
            throw new AppException("Failed to delete employee records. Please try again later." + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee/get-teachers-list")
    public ResponseEntity<List<Map<String, Object>>> getTeachers(){
        try {
            List<Map<String, Object>> teacherList = employeeServiceI.getTeachers();
            return ResponseEntity.ok(teacherList);
        }catch (Exception e){
            throw new AppException("Request Fail With Error:"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
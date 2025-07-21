package com.bit.backend.controllers;

import com.bit.backend.dtos.SchedulerDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.SchedulerServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class SchedulerController {

    private final SchedulerServiceI schedulerServiceI;

    public SchedulerController(SchedulerServiceI schedulerServiceI) {
        this.schedulerServiceI = schedulerServiceI;
    }

    @PutMapping("/scheduler")
    public ResponseEntity<SchedulerDto> saveScheduleForEmployer(@RequestBody SchedulerDto schedulerDto) {
        try {
            SchedulerDto responseSchedulerDto = schedulerServiceI.saveScheduleForEmployer(schedulerDto);
            return ResponseEntity.created(URI.create("/scheduler"+responseSchedulerDto.getEmpNo())).body(responseSchedulerDto);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/scheduler/{empNo}")
    public ResponseEntity<SchedulerDto> getData(@PathVariable String empNo) {
        try {
            SchedulerDto responseSchedulerDto = schedulerServiceI.getData(empNo);
            return ResponseEntity.ok(responseSchedulerDto);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

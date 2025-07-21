package com.bit.backend.controllers;

import com.bit.backend.dtos.SbaMarkDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.SbaMarkServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController

public class SbaMarkController {

    private final SbaMarkServiceI sbaMarkServiceI;

    public SbaMarkController(SbaMarkServiceI sbaMarkServiceI) {
        this.sbaMarkServiceI  = sbaMarkServiceI;
    }

    @PostMapping("/sbamarks")
    public ResponseEntity<SbaMarkDto> addSbaMark(@RequestBody SbaMarkDto sbaMarkDto) {

        try {

            SbaMarkDto sbaMarkDtoResponse = sbaMarkServiceI.addSbaMarkEntity(sbaMarkDto);
            return ResponseEntity.created(URI.create("/sbamarks"+sbaMarkDtoResponse.getAdmissionNumber())).body(sbaMarkDtoResponse);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sbamarks")
    public ResponseEntity<List<SbaMarkDto>> getData(){

        try {

            List<SbaMarkDto> sbaMarkDtoList = sbaMarkServiceI.getData();
            return ResponseEntity.ok(sbaMarkDtoList);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/sbamarks/{id}")
    public ResponseEntity<SbaMarkDto> updateSba(@PathVariable long id, @RequestBody SbaMarkDto sbaMarkDto) {

        try {

            SbaMarkDto responseSbaMarkDto = sbaMarkServiceI.updateSbaMark(id, sbaMarkDto);
            return ResponseEntity.ok(responseSbaMarkDto);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/sbamarks/{id}")
    public ResponseEntity<SbaMarkDto> deleteSba(@PathVariable long id) {

        try {

            SbaMarkDto sbaMarkDto = sbaMarkServiceI.deleteSbaMark(id);
            return ResponseEntity.ok(sbaMarkDto);

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

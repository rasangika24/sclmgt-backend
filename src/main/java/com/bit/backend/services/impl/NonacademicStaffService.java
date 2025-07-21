package com.bit.backend.services.impl;

import com.bit.backend.dtos.NonacademicStaffDto;
import com.bit.backend.entities.NonacademicStaffEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.NonacademicStaffMapper;
import com.bit.backend.repositories.NonacademicStaffRepository;
import com.bit.backend.services.NonacademicStaffServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class NonacademicStaffService implements NonacademicStaffServiceI {

    private final NonacademicStaffRepository nonacademicStaffRepository;
    private final NonacademicStaffMapper nonacademicStaffMapper;

    public NonacademicStaffService(NonacademicStaffRepository nonacademicStaffRepository, NonacademicStaffMapper nonacademicStaffMapper) {
        this.nonacademicStaffRepository = nonacademicStaffRepository;
        this.nonacademicStaffMapper = nonacademicStaffMapper;
    }

    @Override
    public NonacademicStaffDto addNonacademicStaffEntity(NonacademicStaffDto nonacademicStaffDto) {

        System.out.println("********************** BackEnd **********************");

        try {
            NonacademicStaffEntity nonacademicStaffEntity = nonacademicStaffMapper.toNonacademicStaffEntity(nonacademicStaffDto);
            NonacademicStaffEntity savedItem = nonacademicStaffRepository.save(nonacademicStaffEntity);
            NonacademicStaffDto savedDto = nonacademicStaffMapper.toNonacademicStaffDto(savedItem);

            return savedDto;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public List<NonacademicStaffDto> getData() {
     //   System.out.println("In Get Data");

        try {
            List<NonacademicStaffEntity>nonacademicStaffEntityList = nonacademicStaffRepository.findAll();
            List<NonacademicStaffDto> nonacademicStaffDtoList = nonacademicStaffMapper.toNonacademicStaffDtoList(nonacademicStaffEntityList);

            return nonacademicStaffDtoList;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public NonacademicStaffDto updateNonacademicStaff(long id, NonacademicStaffDto nonacademicStaffDto) {

        try {
            Optional<NonacademicStaffEntity> optionalnonacademicstaffEntity = nonacademicStaffRepository.findById(id);
            if(!optionalnonacademicstaffEntity.isPresent()) {
                throw new AppException("Teacher does not Exists", HttpStatus.BAD_REQUEST);
            }

            NonacademicStaffEntity newNonacademicStaffEntity = nonacademicStaffMapper.toNonacademicStaffEntity(nonacademicStaffDto);
            newNonacademicStaffEntity.setId(id);
            NonacademicStaffEntity nonacademicStaffEntity = nonacademicStaffRepository.save(newNonacademicStaffEntity);
            NonacademicStaffDto responseNonacademicStaffDto = nonacademicStaffMapper.toNonacademicStaffDto(nonacademicStaffEntity);

            return responseNonacademicStaffDto;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public NonacademicStaffDto deleteNonacademicStaff(long id) {

        try {

            Optional<NonacademicStaffEntity> optionalNonacademicStaffEntity = nonacademicStaffRepository.findById(id);
            if(!optionalNonacademicStaffEntity.isPresent()) {
                throw new AppException("Teacher does not Exists", HttpStatus.BAD_REQUEST);
            }
            nonacademicStaffRepository.deleteById(id);


            return nonacademicStaffMapper.toNonacademicStaffDto(optionalNonacademicStaffEntity.get());


        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public boolean nicExists(String nic) {

        System.out.println("Non-Academic Staff NIC Checking");

        return nonacademicStaffRepository.existsByNic(nic);
    }
}

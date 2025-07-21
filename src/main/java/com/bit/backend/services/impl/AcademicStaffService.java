package com.bit.backend.services.impl;

import com.bit.backend.dtos.AcademicStaffDto;
import com.bit.backend.entities.AcademicStaffEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AcademicStaffMapper;
import com.bit.backend.repositories.AcademicStaffRepository;
import com.bit.backend.services.AcademicStaffServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AcademicStaffService implements AcademicStaffServiceI {

    private final AcademicStaffRepository academicStaffRepository;
    private final AcademicStaffMapper academicStaffMapper;

    public AcademicStaffService(AcademicStaffRepository academicStaffRepository, AcademicStaffMapper academicStaffMapper) {
        this.academicStaffRepository = academicStaffRepository;
        this.academicStaffMapper = academicStaffMapper;
    }

    @Override
    public AcademicStaffDto addAcademicStaffEntity(AcademicStaffDto academicStaffDto) {

        System.out.println("********************** BackEnd **********************");

        try {
            AcademicStaffEntity academicStaffEntity = academicStaffMapper.toAcademicStaffEntity(academicStaffDto);
            AcademicStaffEntity savedItem = academicStaffRepository.save(academicStaffEntity);
            AcademicStaffDto savedDto = academicStaffMapper.toAcademicStaffDto(savedItem);

            return savedDto;


        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public List<AcademicStaffDto> getData() {
      //  System.out.println("In Get Data");

        try {
            List<AcademicStaffEntity>academicStaffEntityList = academicStaffRepository.findAll();
            List<AcademicStaffDto> academicStaffDtoList = academicStaffMapper.toAcademicStaffDtoList(academicStaffEntityList);

            return academicStaffDtoList;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public AcademicStaffDto updateAcademicStaff(long id, AcademicStaffDto academicStaffDto) {

        try {

            Optional<AcademicStaffEntity> optionalacademicstaffEntity = academicStaffRepository.findById(id);
            if(!optionalacademicstaffEntity.isPresent()) {
                throw new AppException("Teacher does not Exists", HttpStatus.BAD_REQUEST);
            }

            AcademicStaffEntity newAcademicStaffEntity = academicStaffMapper.toAcademicStaffEntity(academicStaffDto);
            newAcademicStaffEntity.setId(id);
            AcademicStaffEntity academicStaffEntity = academicStaffRepository.save(newAcademicStaffEntity);
            AcademicStaffDto responseAcademicStaffDto = academicStaffMapper.toAcademicStaffDto(academicStaffEntity);

            return responseAcademicStaffDto;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public AcademicStaffDto deleteAcademicStaff(long id) {

        try {
            Optional<AcademicStaffEntity> optionalAcademicStaffEntity = academicStaffRepository.findById(id);
            if(!optionalAcademicStaffEntity.isPresent()) {
                throw new AppException("Teacher does not Exists", HttpStatus.BAD_REQUEST);
            }
            academicStaffRepository.deleteById(id);

            return academicStaffMapper.toAcademicStaffDto(optionalAcademicStaffEntity.get());

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public boolean nicExists(String nic) {

        System.out.println("Academic Staff  NIC Checking");

        return academicStaffRepository.existsByNic(nic);
    }

}

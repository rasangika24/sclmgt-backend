package com.bit.backend.services.impl;

import com.bit.backend.dtos.SbaMarkDto;
import com.bit.backend.entities.SbaMarkEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.SbaMarkMapper;
import com.bit.backend.repositories.SbaMarkRepository;
import com.bit.backend.services.SbaMarkServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class SbaMarkService implements SbaMarkServiceI {

    private final SbaMarkRepository sbaMarkRepository;
    private final SbaMarkMapper sbaMarkMapper;

    public SbaMarkService(SbaMarkRepository sbaMarkRepository, SbaMarkMapper sbaMarkMapper) {
        this.sbaMarkRepository = sbaMarkRepository;
        this.sbaMarkMapper = sbaMarkMapper;
    }

    @Override
    public SbaMarkDto addSbaMarkEntity(SbaMarkDto sbaMarkDto){

        try {

            System.out.println("Working");
            SbaMarkEntity sbaMarkEntity = sbaMarkMapper.toSbaMarkEntity(sbaMarkDto);
            SbaMarkEntity savedItem = sbaMarkRepository.save(sbaMarkEntity);
            SbaMarkDto savedDto = sbaMarkMapper.toSbaMarkDto(savedItem);
            return savedDto;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public List<SbaMarkDto> getData() {
        // db oparations and send data
        System.out.println("Sent Data");

        try {

            List <SbaMarkEntity> sbaMarkEntityList = sbaMarkRepository.findAll();
            List<SbaMarkDto> sbaMarkDtoList = sbaMarkMapper.toSbaMarkDtoList(sbaMarkEntityList);

            return sbaMarkDtoList;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public SbaMarkDto updateSbaMark(long id, SbaMarkDto sbaMarkDto) {
        System.out.println("Update Data");

        try {

            Optional<SbaMarkEntity> optionalsbaMarkEntity = sbaMarkRepository.findById(id);
            if(!optionalsbaMarkEntity.isPresent()) {
                throw new AppException("Index Number does not Exists", HttpStatus.BAD_REQUEST);
            }

            SbaMarkEntity newSbaMarkEntity = sbaMarkMapper.toSbaMarkEntity(sbaMarkDto);
            SbaMarkEntity sbaMarkEntity = sbaMarkRepository.save(newSbaMarkEntity);
            SbaMarkDto responseSbaMarkDto = sbaMarkMapper.toSbaMarkDto(sbaMarkEntity);

            return responseSbaMarkDto;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SbaMarkDto deleteSbaMark(long id) {
        System.out.println("Delete Data");

        try {

            Optional<SbaMarkEntity> optionalSbaMarkEntity = sbaMarkRepository.findById(id);
            if(!optionalSbaMarkEntity.isPresent()) {
                throw new AppException("Admission does not Exists", HttpStatus.BAD_REQUEST);
            }
            sbaMarkRepository.deleteById(id);


            return sbaMarkMapper.toSbaMarkDto(optionalSbaMarkEntity.get());

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

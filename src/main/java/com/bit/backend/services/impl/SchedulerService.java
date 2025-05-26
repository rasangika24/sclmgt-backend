package com.bit.backend.services.impl;

import com.bit.backend.dtos.SchedulerDto;
import com.bit.backend.entities.SchedulerEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.SchedulerMapper;
import com.bit.backend.repositories.SchedulerRepository;
import com.bit.backend.services.SchedulerServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchedulerService implements SchedulerServiceI {

    private final SchedulerMapper schedulerMapper;
    private final SchedulerRepository schedulerRepository;

    public SchedulerService(SchedulerMapper schedulerMapper, SchedulerRepository schedulerRepository) {
        this.schedulerMapper = schedulerMapper;
        this.schedulerRepository = schedulerRepository;
    }

    @Override
    public SchedulerDto saveScheduleForEmployer(SchedulerDto schedulerDto) {
        try {
            List<SchedulerEntity> schedulerEntityList = schedulerMapper.toSchedulerEntityList(schedulerDto);

            for (SchedulerEntity schedulerEntity: schedulerEntityList) {
                Optional<SchedulerEntity> optionalSchedulerEntity = schedulerRepository.findById(schedulerEntity.getId());

                if (optionalSchedulerEntity.isPresent()) {
                    SchedulerEntity oldSchedulerEntity = optionalSchedulerEntity.get();
                    oldSchedulerEntity.setMonday(schedulerEntity.getMonday());
                    oldSchedulerEntity.setTuesday(schedulerEntity.getTuesday());
                    oldSchedulerEntity.setWednesday(schedulerEntity.getWednesday());
                    oldSchedulerEntity.setThursday(schedulerEntity.getThursday());
                    oldSchedulerEntity.setFriday(schedulerEntity.getFriday());
                    schedulerRepository.save(oldSchedulerEntity);
                } else {
                    schedulerRepository.save(schedulerEntity);
                }
            }
            return schedulerDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SchedulerDto getData(String empNo) {
        try {
            Optional<List<SchedulerEntity>> optionalSchedulerEntityList = schedulerRepository.findByEmpNo(empNo);
            SchedulerDto schedulerDto;
            if (optionalSchedulerEntityList.isPresent() && !optionalSchedulerEntityList.get().isEmpty()) {
                schedulerDto = schedulerMapper.toSchedulerDto(optionalSchedulerEntityList.get());
                return schedulerDto;
            } else {
                return new SchedulerDto();
            }
        } catch (Exception e) {
            throw new AppException("Request failed with error " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

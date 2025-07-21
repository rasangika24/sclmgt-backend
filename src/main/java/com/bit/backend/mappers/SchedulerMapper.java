package com.bit.backend.mappers;

import com.bit.backend.dtos.PeriodDto;
import com.bit.backend.dtos.SchedulerDto;
import com.bit.backend.entities.SchedulerEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SchedulerMapper {
    default SchedulerDto toSchedulerDto(List<SchedulerEntity> schedulerEntityList) {
        SchedulerDto schedulerDto = new SchedulerDto();
        schedulerDto.setEmpNo(schedulerEntityList.get(0).getEmpNo());

        List<PeriodDto> periodDtoList = new ArrayList<>();

        for (SchedulerEntity schedulerEntity: schedulerEntityList) {
            PeriodDto periodDto = new PeriodDto();
            periodDto.setPeriodNo(schedulerEntity.getPeriodNo());
            periodDto.setMonday(schedulerEntity.getMonday());
            periodDto.setTuesday(schedulerEntity.getTuesday());
            periodDto.setWednesday(schedulerEntity.getWednesday());
            periodDto.setThursday(schedulerEntity.getThursday());
            periodDto.setFriday(schedulerEntity.getFriday());
            periodDto.setId(schedulerEntity.getId().intValue());

            periodDtoList.add(periodDto);
        }

        schedulerDto.setRowData(periodDtoList);
        return schedulerDto;
    }
    default List<SchedulerEntity> toSchedulerEntityList(SchedulerDto schedulerDto) {
        String empNo = schedulerDto.getEmpNo();
        List<SchedulerEntity> schedulerEntityList = new ArrayList<>();

        for (PeriodDto periodDto: schedulerDto.getRowData()) {
            SchedulerEntity schedulerEntity = new SchedulerEntity();
            schedulerEntity.setId((long) periodDto.getId());
            schedulerEntity.setEmpNo(empNo);
            schedulerEntity.setPeriodNo(periodDto.getPeriodNo());
            schedulerEntity.setMonday(periodDto.getMonday());
            schedulerEntity.setTuesday(periodDto.getTuesday());
            schedulerEntity.setWednesday(periodDto.getWednesday());
            schedulerEntity.setThursday(periodDto.getThursday());
            schedulerEntity.setFriday(periodDto.getFriday());

            schedulerEntityList.add(schedulerEntity);
        }
        return schedulerEntityList;
    }
}

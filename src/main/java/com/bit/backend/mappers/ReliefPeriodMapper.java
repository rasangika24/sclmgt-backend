package com.bit.backend.mappers;

import com.bit.backend.dtos.ReliefPeriodDto;
import com.bit.backend.entities.ReliefPeriodEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ReliefPeriodMapper {

    ReliefPeriodDto toReliefPeriodDto(ReliefPeriodEntity reliefPeriodEntity);
    ReliefPeriodEntity toReliefPeriodEntity(ReliefPeriodDto reliefPeriodDto);
    List<ReliefPeriodDto> toReliefPeriodDtoList(List<ReliefPeriodEntity> reliefPeriodEntityList);
}
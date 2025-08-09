package com.bit.backend.mappers;

import com.bit.backend.dtos.TimetableDto;
import com.bit.backend.entities.TimetableEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TimetableMapper {

    TimetableDto toTimetableDto(TimetableEntity timetableEntity);
    TimetableEntity toTimetableEntity(TimetableDto timetableDto);
    List<TimetableDto> toTimetableDtoList(List<TimetableEntity> timetableEntityList);
    List<TimetableEntity> toTimetableEntityList(List<TimetableDto> timetableDtoList);
}
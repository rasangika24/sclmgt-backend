package com.bit.backend.mappers;

import com.bit.backend.dtos.TeacherAbsenceDto;
import com.bit.backend.entities.TeacherAbsenceEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TeacherAbsenceMapper {

    TeacherAbsenceDto toTeacherAbsenceDto(TeacherAbsenceEntity teacherAbsenceEntity);
    TeacherAbsenceEntity toTeacherAbsenceEntity(TeacherAbsenceDto teacherAbsenceDto);
    List<TeacherAbsenceDto> toTeacherAbsenceDtoList(List<TeacherAbsenceEntity> teacherAbsenceEntityList);
}
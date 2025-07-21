package com.bit.backend.mappers;

import com.bit.backend.dtos.GradesGenDto;
import com.bit.backend.entities.GradeGenEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface GradeMapper {

    GradesGenDto toGradeDto(GradeGenEntity gradeGenEntity);
    GradeGenEntity toGradeEntity(GradesGenDto gradesGenDto);
}

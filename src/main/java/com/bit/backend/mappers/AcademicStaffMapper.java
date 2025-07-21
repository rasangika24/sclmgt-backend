package com.bit.backend.mappers;


import com.bit.backend.dtos.AcademicStaffDto;
import com.bit.backend.entities.AcademicStaffEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))

public interface AcademicStaffMapper {

    AcademicStaffDto toAcademicStaffDto(AcademicStaffEntity academicStaffEntity);
    AcademicStaffEntity toAcademicStaffEntity(AcademicStaffDto academicStaffDto);
    List<AcademicStaffDto>toAcademicStaffDtoList(List<AcademicStaffEntity> academicStaffEntityList);

}

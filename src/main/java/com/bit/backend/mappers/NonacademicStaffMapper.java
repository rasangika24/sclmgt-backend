package com.bit.backend.mappers;

import com.bit.backend.dtos.NonacademicStaffDto;
import com.bit.backend.entities.NonacademicStaffEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))

public interface NonacademicStaffMapper  {

    NonacademicStaffDto toNonacademicStaffDto(NonacademicStaffEntity nonacademicStaffEntity);
    NonacademicStaffEntity toNonacademicStaffEntity(NonacademicStaffDto nonacademicStaffDto);
    List<NonacademicStaffDto> toNonacademicStaffDtoList(List<NonacademicStaffEntity> nonacademicStaffEntityList);


}

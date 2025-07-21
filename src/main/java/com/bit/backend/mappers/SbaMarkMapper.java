package com.bit.backend.mappers;

import com.bit.backend.dtos.SbaMarkDto;
import com.bit.backend.entities.SbaMarkEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))

public interface SbaMarkMapper {

    SbaMarkDto toSbaMarkDto (SbaMarkEntity sbaMarkEntity);
    SbaMarkEntity toSbaMarkEntity (SbaMarkDto sbaMarkDto);
    List<SbaMarkDto> toSbaMarkDtoList(List<SbaMarkEntity> sbaMarkEntityList);

}

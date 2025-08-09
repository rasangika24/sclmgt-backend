
// CertificationRequestMapper.java
package com.bit.backend.mappers;

import com.bit.backend.dtos.CertificationRequestDto;
import com.bit.backend.entities.CertificationRequestEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CertificationRequestMapper {

    CertificationRequestDto toCertificationRequestDto(CertificationRequestEntity certificationRequestEntity);
    CertificationRequestEntity toCertificationRequestEntity(CertificationRequestDto certificationRequestDto);
    List<CertificationRequestDto> toCertificationRequestDtoList(List<CertificationRequestEntity> certificationRequestEntityList);
}

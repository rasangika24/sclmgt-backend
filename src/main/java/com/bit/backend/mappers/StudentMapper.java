package com.bit.backend.mappers;


import com.bit.backend.dtos.StudentDto;
import com.bit.backend.entities.StudentEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))

public interface StudentMapper {

    StudentDto toStudentDto (StudentEntity studentEntity);
    StudentEntity toStudentEntity (StudentDto studentDto);
    List<StudentDto>toStudentDtoList(List<StudentEntity> studentEntityList);

}

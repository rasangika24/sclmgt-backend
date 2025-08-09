package com.bit.backend.services;

import com.bit.backend.dtos.StudentDto;

import java.util.List;

public interface StudentServiceI {

    StudentDto addStudentEntity (StudentDto studentDto);
    List<StudentDto> getData();
    StudentDto updateStudent(long id, StudentDto studentDto);
    StudentDto deleteStudent(long id);
    boolean nicExists(String nic);
    StudentDto findByAdmissionNumber(String admissionNumber);

}

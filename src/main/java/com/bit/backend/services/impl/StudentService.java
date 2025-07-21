package com.bit.backend.services.impl;

import com.bit.backend.dtos.StudentDto;
import com.bit.backend.entities.StudentEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.StudentMapper;
import com.bit.backend.repositories.StudentRepository;
import com.bit.backend.services.StudentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class StudentService implements StudentServiceI {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDto addStudentEntity(StudentDto studentDto){

        try {

            System.out.println("Working");
            StudentEntity studentEntity = studentMapper.toStudentEntity(studentDto);
            StudentEntity savedItem = studentRepository.save(studentEntity);
            StudentDto savedDto = studentMapper.toStudentDto(savedItem);
            return savedDto;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public List<StudentDto> getData() {
        // db oparations and send data

        try {

            List <StudentEntity> studentEntityList = studentRepository.findAll();
            List<StudentDto> studentDtoList = studentMapper.toStudentDtoList(studentEntityList);

            return studentDtoList;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public StudentDto updateStudent(long id, StudentDto studentDto) {

        try {

            Optional<StudentEntity> optionalstudentEntity = studentRepository.findById(id);
            if(!optionalstudentEntity.isPresent()) {
                throw new AppException("Student does not Exists", HttpStatus.BAD_REQUEST);
            }

            StudentEntity newStudentEntity = studentMapper.toStudentEntity(studentDto);
            StudentEntity studentEntity = studentRepository.save(newStudentEntity);
            StudentDto responseStudentDto = studentMapper.toStudentDto(studentEntity);

            return responseStudentDto;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public StudentDto deleteStudent(long id) {

        try {

            Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
            if(!optionalStudentEntity.isPresent()) {
                throw new AppException("Student does not Exists", HttpStatus.BAD_REQUEST);
            }
            studentRepository.deleteById(id);


            return studentMapper.toStudentDto(optionalStudentEntity.get());

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean nicExists(String nic) {

        System.out.println("Student NIC Checking");

        return studentRepository.existsByNic(nic);
    }

}

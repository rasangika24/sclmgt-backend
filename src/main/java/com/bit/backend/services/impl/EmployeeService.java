package com.bit.backend.services.impl;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.EmployeeMapper;
import com.bit.backend.repositories.EmployeeRepository;
import com.bit.backend.services.EmployeeServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceI {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    // addEmployeeEntity method
    @Override
    public EmployeeDto addEmployeeEntity(EmployeeDto employeeDto) {
        try {
            System.out.println("************ In Service *************");

            EmployeeEntity employeeEntity = employeeMapper.toEmployeeEntity(employeeDto);
            EmployeeEntity savedItem = employeeRepository.save(employeeEntity);
            EmployeeDto savedEmployeeDto = employeeMapper.toEmployeeDto(savedItem);
            return savedEmployeeDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // getEmployee method
    @Override
    public List<EmployeeDto> getEmployee() {
        try {
            // db operations and send data
            List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
            List<EmployeeDto> employeeDtoList = employeeMapper.toEmployeeDto(employeeEntityList);
            return employeeDtoList;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public EmployeeDto updateEmployee(long id, EmployeeDto employeeDto) {
        try {
            Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);

            if (!optionalEmployeeEntity.isPresent()) {
                throw new AppException("Employee Does Not Exist", HttpStatus.BAD_REQUEST);
            }

            EmployeeEntity newEmployeeEntity = employeeMapper.toEmployeeEntity(employeeDto);
            newEmployeeEntity.setId(id);
            EmployeeEntity employeeEntity = employeeRepository.save(newEmployeeEntity);
            EmployeeDto responseEmployeeDto = employeeMapper.toEmployeeDto(employeeEntity);
            return responseEmployeeDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public EmployeeDto deleteEmployee(long id) {
        try {
            Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);

            if (!optionalEmployeeEntity.isPresent()) {
                throw new AppException("Employee Does Not Exsist", HttpStatus.BAD_REQUEST);
            }

            employeeRepository.deleteById(id);

            EmployeeDto employeeDto = employeeMapper.toEmployeeDto(optionalEmployeeEntity.get());
            return employeeDto;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.bit.backend.services;

import com.bit.backend.dtos.GradesGenDto;

import java.util.List;

public interface GradesServiceI {
    List<GradesGenDto> generateGrades(GradesGenDto gradesGenDto);
}

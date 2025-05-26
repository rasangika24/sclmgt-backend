package com.bit.backend.services.impl;

import com.bit.backend.dtos.GradesGenDto;
import com.bit.backend.entities.GradeGenEntity;
import com.bit.backend.mappers.GradeMapper;
import com.bit.backend.repositories.GradesRepository;
import com.bit.backend.services.GradesServiceI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService implements GradesServiceI {
    private final GradesRepository gradesRepository;
    private final GradeMapper gradeMapper;

    public GradeService(GradesRepository gradesRepository, GradeMapper gradeMapper) {
        this.gradesRepository = gradesRepository;
        this.gradeMapper = gradeMapper;
    }

    @Override
    public List<GradesGenDto> generateGrades(GradesGenDto gradesGenDto) {
        int fromGrade = gradesGenDto.getFromGrade();
        int toGrade = gradesGenDto.getToGrade();

        List<GradesGenDto> gradesGenDtoList = new ArrayList<>();

        for (int grade = fromGrade; grade <= toGrade; grade++) {
            GradeGenEntity gradeGenEntity = new GradeGenEntity();
            gradeGenEntity.setGrade(grade);
            GradesGenDto newGradeGenDto = this.gradeMapper.toGradeDto(this.gradesRepository.save(gradeGenEntity));
            gradesGenDtoList.add(newGradeGenDto);
        }

        return gradesGenDtoList;
    }
}

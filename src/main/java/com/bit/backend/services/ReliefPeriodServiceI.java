package com.bit.backend.services;

import com.bit.backend.dtos.AvailableTeacherDto;
import com.bit.backend.dtos.ReliefPeriodDto;
import com.bit.backend.dtos.TeacherAbsenceDto;
import com.bit.backend.dtos.TimetableDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReliefPeriodServiceI {

    // Absence Management
    TeacherAbsenceDto markTeacherAbsent(TeacherAbsenceDto teacherAbsenceDto);
    List<TeacherAbsenceDto> getAbsentTeachers(LocalDate date);
    List<TeacherAbsenceDto> getAllActiveAbsences();

    // Timetable for Absent Teachers
    List<TimetableDto> getAbsentTeacherTimetable(String teacherId, LocalDate date);

    // Available Teachers
    List<AvailableTeacherDto> getAvailableTeachers(LocalDate date, Integer periodNumber);

    // Relief Period Management
    ReliefPeriodDto assignReliefTeacher(ReliefPeriodDto reliefPeriodDto);
    List<ReliefPeriodDto> getReliefPeriods(LocalDate date);
    List<ReliefPeriodDto> getReliefPeriodsByTeacher(String teacherId, LocalDate date);
    ReliefPeriodDto updateReliefPeriod(Long id, ReliefPeriodDto reliefPeriodDto);
    ReliefPeriodDto cancelReliefPeriod(Long id);

    // Reports
    Map<String, Object> getReliefPeriodReport(LocalDate startDate, LocalDate endDate);
}
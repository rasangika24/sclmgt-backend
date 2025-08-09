package com.bit.backend.services;

import com.bit.backend.dtos.TimeSlotDto;
import com.bit.backend.dtos.TimetableDto;
import com.bit.backend.dtos.TimetableWeeklyDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TimetableServiceI {

    // Your existing methods...
    TimetableDto addTimetable(TimetableDto timetableDto);
    List<TimetableDto> getAllTimetables();
    TimetableDto getTimetableById(Long id);
    TimetableDto updateTimetable(Long id, TimetableDto timetableDto);
    TimetableDto deleteTimetable(Long id);

    List<TimetableDto> getTimetablesByTeacherId(String teacherId);
    TimetableWeeklyDto getWeeklyTimetableByTeacherId(String teacherId);
    List<TimetableDto> getTimetablesByTeacherIdAndAcademicYear(String teacherId, String academicYear);
    List<TimetableDto> getTimetablesByTeacherIdAndTerm(String teacherId, String academicYear, String term);
    List<TimetableDto> getDailyTimetableByTeacherId(String teacherId, String dayOfWeek);

    List<TimetableDto> searchTimetablesByTeacherName(String teacherName);
    List<TimetableDto> getTimetablesBySubject(String subject);
    List<TimetableDto> getTimetablesByGrade(String grade);
    List<TimetableDto> getTimetablesByAcademicYearAndTerm(String academicYear, String term);

    boolean checkTimeConflict(String teacherId, String dayOfWeek, String startTime, String endTime, Long excludeId);
    List<String> getAvailableTimeSlots(String teacherId, String dayOfWeek);
    List<TimeSlotDto> getDetailedAvailableTimeSlots(String teacherId, String dayOfWeek);
    List<TimeSlotDto> getAllStandardTimeSlots();

    // NEW ENHANCED METHODS
    TimeSlotDto getTimeSlotByPeriod(Integer periodNumber);
    List<TimeSlotDto> getFreePeriodsByDay(String teacherId, String dayOfWeek);
    Map<String, List<TimeSlotDto>> getFreePeriodsWeek(String teacherId);
    Map<String, Object> getTeacherWorkloadStats(String teacherId);
    List<String> getAvailableTeachers(String dayOfWeek, Integer periodNumber);
    List<TimetableDto> getRoomSchedule(String classRoom, String dayOfWeek);
    List<Map<String, Object>> getFreeTeachersForPeriod(LocalDate date, Integer periodNumber);
}
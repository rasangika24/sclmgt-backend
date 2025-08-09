package com.bit.backend.dtos;

import java.util.List;
import java.util.Map;

public class TimetableWeeklyDto {

    private String teacherId;
    private String teacherName;
    private String academicYear;
    private String term;
    private Map<String, List<TimetableDto>> weeklySchedule; // Key: Day, Value: List of periods

    public TimetableWeeklyDto() {}

    public TimetableWeeklyDto(String teacherId, String teacherName, String academicYear,
                              String term, Map<String, List<TimetableDto>> weeklySchedule) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.academicYear = academicYear;
        this.term = term;
        this.weeklySchedule = weeklySchedule;
    }

    // Getters and Setters
    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }

    public Map<String, List<TimetableDto>> getWeeklySchedule() { return weeklySchedule; }
    public void setWeeklySchedule(Map<String, List<TimetableDto>> weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }
}
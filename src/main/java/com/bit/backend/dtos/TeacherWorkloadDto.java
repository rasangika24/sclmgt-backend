package com.bit.backend.dtos;

import java.util.List;
import java.util.Map;

public class TeacherWorkloadDto {
    private String teacherId;
    private String teacherName;
    private int totalClasses;
    private int totalFreeSlots;
    private double utilizationRate;
    private Map<String, Long> classesByDay;
    private Map<String, Long> classesBySubject;
    private Map<String, List<TimeSlotDto>> weeklyFreePeriods;

    // Constructors, getters, and setters
    public TeacherWorkloadDto() {}

    public TeacherWorkloadDto(String teacherId, String teacherName, int totalClasses,
                              int totalFreeSlots, double utilizationRate,
                              Map<String, Long> classesByDay, Map<String, Long> classesBySubject,
                              Map<String, List<TimeSlotDto>> weeklyFreePeriods) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.totalClasses = totalClasses;
        this.totalFreeSlots = totalFreeSlots;
        this.utilizationRate = utilizationRate;
        this.classesByDay = classesByDay;
        this.classesBySubject = classesBySubject;
        this.weeklyFreePeriods = weeklyFreePeriods;
    }

    // Getters and Setters
    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public int getTotalClasses() { return totalClasses; }
    public void setTotalClasses(int totalClasses) { this.totalClasses = totalClasses; }

    public int getTotalFreeSlots() { return totalFreeSlots; }
    public void setTotalFreeSlots(int totalFreeSlots) { this.totalFreeSlots = totalFreeSlots; }

    public double getUtilizationRate() { return utilizationRate; }
    public void setUtilizationRate(double utilizationRate) { this.utilizationRate = utilizationRate; }

    public Map<String, Long> getClassesByDay() { return classesByDay; }
    public void setClassesByDay(Map<String, Long> classesByDay) { this.classesByDay = classesByDay; }

    public Map<String, Long> getClassesBySubject() { return classesBySubject; }
    public void setClassesBySubject(Map<String, Long> classesBySubject) { this.classesBySubject = classesBySubject; }

    public Map<String, List<TimeSlotDto>> getWeeklyFreePeriods() { return weeklyFreePeriods; }
    public void setWeeklyFreePeriods(Map<String, List<TimeSlotDto>> weeklyFreePeriods) {
        this.weeklyFreePeriods = weeklyFreePeriods;
    }
}
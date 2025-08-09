// File: TimetableDto.java
package com.bit.backend.dtos;

import java.time.LocalTime;

public class TimetableDto {

    private Long id;
    private String teacherId;
    private String teacherName;
    private String dayOfWeek;
    private Integer periodNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private String subject;
    private String grade;
    private String classRoom;
    private String academicYear;
    private String term;
    private Integer status;

    // Constructors
    public TimetableDto() {}

    public TimetableDto(Long id, String teacherId, String teacherName, String dayOfWeek,
                        Integer periodNumber, LocalTime startTime, LocalTime endTime,
                        String subject, String grade, String classRoom, String academicYear,
                        String term, Integer status) {
        this.id = id;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.dayOfWeek = dayOfWeek;
        this.periodNumber = periodNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.grade = grade;
        this.classRoom = classRoom;
        this.academicYear = academicYear;
        this.term = term;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public Integer getPeriodNumber() { return periodNumber; }
    public void setPeriodNumber(Integer periodNumber) { this.periodNumber = periodNumber; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getClassRoom() { return classRoom; }
    public void setClassRoom(String classRoom) { this.classRoom = classRoom; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}

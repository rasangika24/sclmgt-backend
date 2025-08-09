package com.bit.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReliefPeriodDto {

    private Long id;
    private String absentTeacherId;
    private String absentTeacherName;
    private String reliefTeacherId;
    private String reliefTeacherName;
    private LocalDate date;
    private String dayOfWeek;
    private Integer periodNumber;
    private String subject;
    private String grade;
    private String classRoom;
    private String status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String assignedBy;

    // Constructors
    public ReliefPeriodDto() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAbsentTeacherId() { return absentTeacherId; }
    public void setAbsentTeacherId(String absentTeacherId) { this.absentTeacherId = absentTeacherId; }

    public String getAbsentTeacherName() { return absentTeacherName; }
    public void setAbsentTeacherName(String absentTeacherName) { this.absentTeacherName = absentTeacherName; }

    public String getReliefTeacherId() { return reliefTeacherId; }
    public void setReliefTeacherId(String reliefTeacherId) { this.reliefTeacherId = reliefTeacherId; }

    public String getReliefTeacherName() { return reliefTeacherName; }
    public void setReliefTeacherName(String reliefTeacherName) { this.reliefTeacherName = reliefTeacherName; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public Integer getPeriodNumber() { return periodNumber; }
    public void setPeriodNumber(Integer periodNumber) { this.periodNumber = periodNumber; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getClassRoom() { return classRoom; }
    public void setClassRoom(String classRoom) { this.classRoom = classRoom; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getAssignedBy() { return assignedBy; }
    public void setAssignedBy(String assignedBy) { this.assignedBy = assignedBy; }
}
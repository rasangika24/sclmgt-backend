package com.bit.backend.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TeacherAbsenceDto {

    private Long id;
    private String teacherId;
    private String teacherName;
    private LocalDate absenceDate;
    private String reason;
    private String status;
    private LocalDateTime createdAt;
    private String markedBy;

    // Constructors
    public TeacherAbsenceDto() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public LocalDate getAbsenceDate() { return absenceDate; }
    public void setAbsenceDate(LocalDate absenceDate) { this.absenceDate = absenceDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getMarkedBy() { return markedBy; }
    public void setMarkedBy(String markedBy) { this.markedBy = markedBy; }
}
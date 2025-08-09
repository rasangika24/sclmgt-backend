package com.bit.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "relief_periods")
public class ReliefPeriodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "absent_teacher_id", nullable = false)
    private String absentTeacherId;

    @Column(name = "absent_teacher_name", nullable = false)
    private String absentTeacherName;

    @Column(name = "relief_teacher_id")
    private String reliefTeacherId;

    @Column(name = "relief_teacher_name")
    private String reliefTeacherName;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

    @Column(name = "period_number", nullable = false)
    private Integer periodNumber;

    @Column(name = "subject")
    private String subject;

    @Column(name = "grade")
    private String grade;

    @Column(name = "class_room")
    private String classRoom;

    @Column(name = "status")
    private String status; // PENDING, ASSIGNED, COMPLETED, CANCELLED

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "assigned_by")
    private String assignedBy;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = "PENDING";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Constructors
    public ReliefPeriodEntity() {}

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

package com.bit.backend.entities;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "teacher_timetable")
public class TimetableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_id", nullable = false)
    private String teacherId;

    @Column(name = "teacher_name", nullable = false)
    private String teacherName;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek; // MONDAY, TUESDAY, etc.

    @Column(name = "period_number", nullable = false)
    private Integer periodNumber;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "grade", nullable = false)
    private String grade;

    @Column(name = "class_room")
    private String classRoom;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "term")
    private String term;

    @Column(name = "status", nullable = false)
    private Integer status = 1; // 1 = active, 0 = inactive

    // Constructors
    public TimetableEntity() {}

    public TimetableEntity(Long id, String teacherId, String teacherName, String dayOfWeek,
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
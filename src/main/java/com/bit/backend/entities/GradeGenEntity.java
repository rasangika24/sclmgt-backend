package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Grades")
public class GradeGenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade")
    private int grade;

    public GradeGenEntity() {
    }

    public GradeGenEntity(Long id, int grade) {
        this.id = id;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

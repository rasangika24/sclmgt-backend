package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name="sbamark")

public class SbaMarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fileNumber") private Long fileNumber;
    @Column(name = "exam") private String exam;
    @Column(name = "admissionNumber") private String admissionNumber;
    @Column(name = "year") private String year;
    @Column(name = "subject1") private Long subject1;
    @Column(name = "subject2") private String subject2;
    @Column(name = "subject3") private String subject3;
    @Column(name = "subject4") private String subject4;
    @Column(name = "subject5") private String subject5;
    @Column(name = "subject6") private String subject6;
    @Column(name = "subject7") private String subject7;
    @Column(name = "subject8") private String subject8;
    @Column(name = "subject9") private String subject9;
    @Column(name = "subject10") private String subject10;

    public SbaMarkEntity() {
    }

    public SbaMarkEntity(Long id, Long fileNumber, String exam, String admissionNumber, String year, Long subject1, String subject2, String subject3, String subject4, String subject5, String subject6, String subject7, String subject8, String subject9, String subject10) {
        this.id = id;
        this.fileNumber = fileNumber;
        this.exam = exam;
        this.admissionNumber = admissionNumber;
        this.year = year;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.subject4 = subject4;
        this.subject5 = subject5;
        this.subject6 = subject6;
        this.subject7 = subject7;
        this.subject8 = subject8;
        this.subject9 = subject9;
        this.subject10 = subject10;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(Long fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getSubject1() {
        return subject1;
    }

    public void setSubject1(Long subject1) {
        this.subject1 = subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public String getSubject3() {
        return subject3;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    public String getSubject4() {
        return subject4;
    }

    public void setSubject4(String subject4) {
        this.subject4 = subject4;
    }

    public String getSubject5() {
        return subject5;
    }

    public void setSubject5(String subject5) {
        this.subject5 = subject5;
    }

    public String getSubject6() {
        return subject6;
    }

    public void setSubject6(String subject6) {
        this.subject6 = subject6;
    }

    public String getSubject7() {
        return subject7;
    }

    public void setSubject7(String subject7) {
        this.subject7 = subject7;
    }

    public String getSubject8() {
        return subject8;
    }

    public void setSubject8(String subject8) {
        this.subject8 = subject8;
    }

    public String getSubject9() {
        return subject9;
    }

    public void setSubject9(String subject9) {
        this.subject9 = subject9;
    }

    public String getSubject10() {
        return subject10;
    }

    public void setSubject10(String subject10) {
        this.subject10 = subject10;
    }
}

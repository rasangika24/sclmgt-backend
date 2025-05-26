package com.bit.backend.dtos;

public class GradesGenDto {
    private Long id;
    private int fromGrade;
    private int toGrade;

    public GradesGenDto() {
    }

    public GradesGenDto(Long id, int fromGrade, int toGrade) {
        this.id = id;
        this.fromGrade = fromGrade;
        this.toGrade = toGrade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFromGrade() {
        return fromGrade;
    }

    public void setFromGrade(int fromGrade) {
        this.fromGrade = fromGrade;
    }

    public int getToGrade() {
        return toGrade;
    }

    public void setToGrade(int toGrade) {
        this.toGrade = toGrade;
    }
}

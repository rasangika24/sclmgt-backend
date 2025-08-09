// ============================================
// 5. DTO - AvailableTeacherDto.java
// ============================================
package com.bit.backend.dtos;

public class AvailableTeacherDto {

    private String teacherId;
    private String teacherName;
    private Boolean isAvailable;
    private Integer freePeriods;

    public AvailableTeacherDto() {}

    public AvailableTeacherDto(String teacherId, String teacherName, Boolean isAvailable, Integer freePeriods) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.isAvailable = isAvailable;
        this.freePeriods = freePeriods;
    }

    // Getters and Setters
    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getTeacherName() { return teacherName; }
    public void setTeacherName(String teacherName) { this.teacherName = teacherName; }

    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }

    public Integer getFreePeriods() { return freePeriods; }
    public void setFreePeriods(Integer freePeriods) { this.freePeriods = freePeriods; }
}
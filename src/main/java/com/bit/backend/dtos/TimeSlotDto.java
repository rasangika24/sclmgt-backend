package com.bit.backend.dtos;

import java.time.LocalTime;

public class TimeSlotDto {

    private Integer periodNumber;
    private LocalTime startTime;
    private LocalTime endTime;
    private String timeSlotLabel;
    private boolean isAvailable;

    public TimeSlotDto() {}

    public TimeSlotDto(Integer periodNumber, LocalTime startTime, LocalTime endTime, String timeSlotLabel, boolean isAvailable) {
        this.periodNumber = periodNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeSlotLabel = timeSlotLabel;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public Integer getPeriodNumber() { return periodNumber; }
    public void setPeriodNumber(Integer periodNumber) { this.periodNumber = periodNumber; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getTimeSlotLabel() { return timeSlotLabel; }
    public void setTimeSlotLabel(String timeSlotLabel) { this.timeSlotLabel = timeSlotLabel; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
package com.bit.backend.services;

import com.bit.backend.dtos.SchedulerDto;

public interface SchedulerServiceI {

    SchedulerDto saveScheduleForEmployer(SchedulerDto schedulerDto);
    SchedulerDto getData(String empNo);
}

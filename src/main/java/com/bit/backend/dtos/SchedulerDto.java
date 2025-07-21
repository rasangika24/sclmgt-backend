package com.bit.backend.dtos;

import java.util.List;

public class SchedulerDto {

    private String empNo;
    private List<PeriodDto> rowData;

    public SchedulerDto() {}

    public SchedulerDto(String empNo, List<PeriodDto> rowData) {
        this.empNo = empNo;
        this.rowData = rowData;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public List<PeriodDto> getRowData() {
        return rowData;
    }

    public void setRowData(List<PeriodDto> rowData) {
        this.rowData = rowData;
    }
}

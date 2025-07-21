package com.bit.backend.services;
import com.bit.backend.dtos.NonacademicStaffDto;
import java.util.List;

public interface NonacademicStaffServiceI {

    NonacademicStaffDto addNonacademicStaffEntity(NonacademicStaffDto nonacademicStaffDto);
    List<NonacademicStaffDto> getData();
    NonacademicStaffDto updateNonacademicStaff(long id, NonacademicStaffDto nonacademicStaffDto);
    NonacademicStaffDto deleteNonacademicStaff(long id);
    boolean nicExists(String nic);

}

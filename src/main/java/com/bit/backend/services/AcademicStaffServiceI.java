package com.bit.backend.services;
import com.bit.backend.dtos.AcademicStaffDto;
import java.util.List;

public interface AcademicStaffServiceI {

    AcademicStaffDto addAcademicStaffEntity(AcademicStaffDto academicStaffDto);
    List<AcademicStaffDto>getData();
    AcademicStaffDto updateAcademicStaff(long id, AcademicStaffDto academicStaffDto);
    AcademicStaffDto deleteAcademicStaff(long id);
    boolean nicExists(String nic);

}

package com.bit.backend.dtos;

import java.time.LocalDate;

public class EmployeeDto {

    private Long id;
    private String empNo;
    private String empType;
    private String nic;
    private String fullName;
    private String callingName;
    private LocalDate dob;
    private int age;
    private String address;
    private String contactNo;
    private String civilStatus;
    private String gender;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String empNo, String empType, String nic, String fullName, String callingName, LocalDate dob, int age, String address, String contactNo, String civilStatus, String gender) {
        this.id = id;
        this.empNo = empNo;
        this.empType = empType;
        this.nic = nic;
        this.fullName = fullName;
        this.callingName = callingName;
        this.dob = dob;
        this.age = age;
        this.address = address;
        this.contactNo = contactNo;
        this.civilStatus = civilStatus;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCallingName() {
        return callingName;
    }

    public void setCallingName(String callingName) {
        this.callingName = callingName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

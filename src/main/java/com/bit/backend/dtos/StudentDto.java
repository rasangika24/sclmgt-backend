package com.bit.backend.dtos;

import java.time.LocalDate;

public class StudentDto {

    private Long id;
    private Long admissionNumber;
    private String nameinFull;
    private String nameWithInitials;
    private LocalDate dateOfBirth;
    private LocalDate dateofAdmission;
    private String gender;
    private String address;
    private Integer telephone;
    private Integer whatsapp;
    private String emailAddress;
    private String nic;
    private String schoolStudied;

    //mothers section
    private String mothersName;
    private String mothersSchool;
    private String mothersEducationLevel;
    private String mothersProfession;
    private Integer mothersTelephone;
    private Integer mothersWhatsapp;

    //fathersection
    private String fathersName;
    private String fathersSchool;
    private String fathersEducationLevel;
    private String fathersProfession;
    private Integer fathersTelephone;
    private Integer fathersWhatsapp;

    //Gardian section
    private String guardiansName;
    private String guardiansSchool;
    private String guardiansEducationLevel;
    private String guardiansProfession;
    private Integer  guardiansTelephone;
    private Integer guardiansWhatsapp;

    public StudentDto(){
    }

    public StudentDto(Long id, Long admissionNumber, String nameinFull, String nameWithInitials, LocalDate dateOfBirth, LocalDate dateofAdmission, String gender, String address, Integer telephone, Integer whatsapp, String emailAddress, String nic, String schoolStudied, String mothersName, String mothersSchool, String mothersEducationLevel, String mothersProfession, Integer mothersTelephone, Integer mothersWhatsapp, String fathersName, String fathersSchool, String fathersEducationLevel, String fathersProfession, Integer fathersTelephone, Integer fathersWhatsapp, String guardiansName, String guardiansSchool, String guardiansEducationLevel, String guardiansProfession, Integer guardiansTelephone, Integer guardiansWhatsapp) {
        this.id = id;
        this.admissionNumber = admissionNumber;
        this.nameinFull = nameinFull;
        this.nameWithInitials = nameWithInitials;
        this.dateOfBirth = dateOfBirth;
        this.dateofAdmission = dateofAdmission;
        this.gender = gender;
        this.address = address;
        this.telephone = telephone;
        this.whatsapp = whatsapp;
        this.emailAddress = emailAddress;
        this.nic = nic;
        this.schoolStudied = schoolStudied;
        this.mothersName = mothersName;
        this.mothersSchool = mothersSchool;
        this.mothersEducationLevel = mothersEducationLevel;
        this.mothersProfession = mothersProfession;
        this.mothersTelephone = mothersTelephone;
        this.mothersWhatsapp = mothersWhatsapp;
        this.fathersName = fathersName;
        this.fathersSchool = fathersSchool;
        this.fathersEducationLevel = fathersEducationLevel;
        this.fathersProfession = fathersProfession;
        this.fathersTelephone = fathersTelephone;
        this.fathersWhatsapp = fathersWhatsapp;
        this.guardiansName = guardiansName;
        this.guardiansSchool = guardiansSchool;
        this.guardiansEducationLevel = guardiansEducationLevel;
        this.guardiansProfession = guardiansProfession;
        this.guardiansTelephone = guardiansTelephone;
        this.guardiansWhatsapp = guardiansWhatsapp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(Long admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getNameinFull() {
        return nameinFull;
    }

    public void setNameinFull(String nameinFull) {
        this.nameinFull = nameinFull;
    }

    public String getNameWithInitials() {
        return nameWithInitials;
    }

    public void setNameWithInitials(String nameWithInitials) {
        this.nameWithInitials = nameWithInitials;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateofAdmission() {
        return dateofAdmission;
    }

    public void setDateofAdmission(LocalDate dateofAdmission) {
        this.dateofAdmission = dateofAdmission;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public Integer getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(Integer whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getSchoolStudied() {
        return schoolStudied;
    }

    public void setSchoolStudied(String schoolStudied) {
        this.schoolStudied = schoolStudied;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getMothersSchool() {
        return mothersSchool;
    }

    public void setMothersSchool(String mothersSchool) {
        this.mothersSchool = mothersSchool;
    }

    public String getMothersEducationLevel() {
        return mothersEducationLevel;
    }

    public void setMothersEducationLevel(String mothersEducationLevel) {
        this.mothersEducationLevel = mothersEducationLevel;
    }

    public String getMothersProfession() {
        return mothersProfession;
    }

    public void setMothersProfession(String mothersProfession) {
        this.mothersProfession = mothersProfession;
    }

    public Integer getMothersTelephone() {
        return mothersTelephone;
    }

    public void setMothersTelephone(Integer mothersTelephone) {
        this.mothersTelephone = mothersTelephone;
    }

    public Integer getMothersWhatsapp() {
        return mothersWhatsapp;
    }

    public void setMothersWhatsapp(Integer mothersWhatsapp) {
        this.mothersWhatsapp = mothersWhatsapp;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getFathersSchool() {
        return fathersSchool;
    }

    public void setFathersSchool(String fathersSchool) {
        this.fathersSchool = fathersSchool;
    }

    public String getFathersEducationLevel() {
        return fathersEducationLevel;
    }

    public void setFathersEducationLevel(String fathersEducationLevel) {
        this.fathersEducationLevel = fathersEducationLevel;
    }

    public String getFathersProfession() {
        return fathersProfession;
    }

    public void setFathersProfession(String fathersProfession) {
        this.fathersProfession = fathersProfession;
    }

    public Integer getFathersTelephone() {
        return fathersTelephone;
    }

    public void setFathersTelephone(Integer fathersTelephone) {
        this.fathersTelephone = fathersTelephone;
    }

    public Integer getFathersWhatsapp() {
        return fathersWhatsapp;
    }

    public void setFathersWhatsapp(Integer fathersWhatsapp) {
        this.fathersWhatsapp = fathersWhatsapp;
    }

    public String getGuardiansName() {
        return guardiansName;
    }

    public void setGuardiansName(String guardiansName) {
        this.guardiansName = guardiansName;
    }

    public String getGuardiansSchool() {
        return guardiansSchool;
    }

    public void setGuardiansSchool(String guardiansSchool) {
        this.guardiansSchool = guardiansSchool;
    }

    public String getGuardiansEducationLevel() {
        return guardiansEducationLevel;
    }

    public void setGuardiansEducationLevel(String guardiansEducationLevel) {
        this.guardiansEducationLevel = guardiansEducationLevel;
    }

    public String getGuardiansProfession() {
        return guardiansProfession;
    }

    public void setGuardiansProfession(String guardiansProfession) {
        this.guardiansProfession = guardiansProfession;
    }

    public Integer getGuardiansTelephone() {
        return guardiansTelephone;
    }

    public void setGuardiansTelephone(Integer guardiansTelephone) {
        this.guardiansTelephone = guardiansTelephone;
    }

    public Integer getGuardiansWhatsapp() {
        return guardiansWhatsapp;
    }

    public void setGuardiansWhatsapp(Integer guardiansWhatsapp) {
        this.guardiansWhatsapp = guardiansWhatsapp;
    }
}

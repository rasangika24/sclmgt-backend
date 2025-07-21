package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admissionNumber")
    private String admissionNumber;

    @Column(name = "NameinFull")
    private String NameinFull;

    @Column(name = "nameWithInitials")
    private String nameWithInitials;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone")
    private Integer telephone;

    @Column(name = "whatsapp")
    private Integer whatsapp;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "nic")
    private String nic;

    @Column(name = "schoolStudied")
    private String schoolStudied;

    @Column(name = "mothersName")
    private String mothersName;

    @Column(name = "mothersSchool")
    private String mothersSchool;

    @Column(name = "mothersEducationLevel")
    private String mothersEducationLevel;

    @Column(name = "mothersProfession")
    private String mothersProfession;

    @Column(name = "mothersTelephone")
    private Integer mothersTelephone;

    @Column(name = "mothersWhatsapp")
    private Integer mothersWhatsapp;

    @Column(name = "fathersName")
    private String fathersName;

    @Column(name = "fathersSchool")
    private String fathersSchool;

    @Column(name = "fathersEducationLevel")
    private String fathersEducationLevel;

    @Column(name = "fathersProfession")
    private String fathersProfession;

    @Column(name = "fathersTelephone")
    private Integer fathersTelephone;

    @Column(name = "fathersWhatsapp")
    private Integer fathersWhatsapp;

    @Column(name = "guardiansName")
    private String guardiansName;

    @Column(name = "guardiansSchool")
    private String guardiansSchool;

    @Column(name = "guardiansEducationLevel")
    private String guardiansEducationLevel;

    @Column(name = "guardiansProfession")
    private String guardiansProfession;

    @Column(name = "guardiansTelephone")
    private Integer guardiansTelephone;

    @Column(name = "guardiansWhatsapp")
    private Integer guardiansWhatsapp;


    public StudentEntity() {
    }

    public StudentEntity(Long id, String admissionNumber, String nameinFull, String nameWithInitials, String dateOfBirth, String address, Integer telephone, Integer whatsapp, String emailAddress, String nic, String schoolStudied, String mothersName, String mothersSchool, String mothersEducationLevel, String mothersProfession, Integer mothersTelephone, Integer mothersWhatsapp, String fathersName, String fathersSchool, String fathersEducationLevel, String fathersProfession, Integer fathersTelephone, Integer fathersWhatsapp, String guardiansName, String guardiansSchool, String guardiansEducationLevel, String guardiansProfession, Integer guardiansTelephone, Integer guardiansWhatsapp) {
        this.id = id;
        this.admissionNumber = admissionNumber;
        this.NameinFull = nameinFull;
        this.nameWithInitials = nameWithInitials;
        this.dateOfBirth = dateOfBirth;
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

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getNameinFull() {
        return NameinFull;
    }

    public void setNameinFull(String nameinFull) {
        NameinFull = nameinFull;
    }

    public String getNameWithInitials() {
        return nameWithInitials;
    }

    public void setNameWithInitials(String nameWithInitials) {
        this.nameWithInitials = nameWithInitials;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "nonacademicstaff")

public class NonacademicStaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacherNumber") private Long teacherNumber;
    @Column(name = "firstAppointDate") private String firstAppointDate;
    @Column(name = "nameInFull") private String nameInFull;
    @Column(name = "nameWithInitials") private String nameWithInitials;
    @Column(name = "usingName") private String usingName;
    @Column(name = "appointmentDateAsTemperary") private String appointmentDateAsTemperary;
    @Column(name = "appointmentDateAsEpf") private String appointmentDateAsEpf;
    @Column(name = "dateOfBirth") private String dateOfBirth;
    @Column(name = "address") private String address;
    @Column(name = "telephone") private Integer telephone;
    @Column(name = "whatsapp") private Integer whatsapp;
    @Column(name = "emailAddress") private String emailAddress;
    @Column(name = "nic") private String nic;
    @Column(name = "schoolStudied") private String schoolStudied;
    @Column(name = "highestEducationQualification") private String highestEducationQualification;
    @Column(name = "professionalQualification") private String professionalQualification;
    @Column(name = "otherQualification") private String otherQualification;
    @Column(name = "extraCurricularActivities") private String extraCurricularActivities;
    @Column(name = "subjectTeaching1") private String subjectTeaching1;
    @Column(name = "marriedOrNot") private String marriedOrNot;
    @Column(name = "dateGotMarried") private String dateGotMarried;
    @Column(name = "statusOfMarriage") private String statusOfMarriage;
    @Column(name = "nameOfTheSpouse") private String nameOfTheSpouse;
    @Column(name = "spouseSchool") private String spouseSchool;
    @Column(name = "spouseHighestEducationLevel") private String spouseHighestEducationLevel;
    @Column(name = "contactNumber") private Integer contactNumber;
    @Column(name = "occupation") private String occupation;
    @Column(name = "numberOfChildren") private String numberOfChildren;
    @Column(name = "educationQualification") private String educationQualification;
    @Column(name = "universityOrInstitute") private String universityOrInstitute;
    @Column(name = "year") private String year;
    @Column(name = "subject") private String subject;
    @Column(name = "mothersName") private String mothersName;
    @Column(name = "mothersSchool") private String mothersSchool;
    @Column(name = "mothersEducationLevel") private String mothersEducationLevel;
    @Column(name = "mothersProfession") private String mothersProfession;
    @Column(name = "mothersTelephone") private Integer mothersTelephone;
    @Column(name = "mothersWhatsapp") private Integer mothersWhatsapp;
    @Column(name = "fathersName") private String fathersName;
    @Column(name = "fathersSchool") private String fathersSchool;
    @Column(name = "fathersEducationLevel") private String fathersEducationLevel;
    @Column(name = "fathersProfession") private String fathersProfession;
    @Column(name = "fathersTelephone") private Integer fathersTelephone;
    @Column(name = "fathersWhatsapp") private Integer fathersWhatsapp;
    @Column(name = "smothersName") private String smothersName;
    @Column(name = "smothersSchool") private String smothersSchool;
    @Column(name = "smothersEducationLevel") private String smothersEducationLevel;
    @Column(name = "smothersProfession") private String smothersProfession;
    @Column(name = "smothersTelephone") private Integer smothersTelephone;
    @Column(name = "smothersWhatsapp") private Integer smothersWhatsapp;
    @Column(name = "sfathersName") private String sfathersName;
    @Column(name = "sfathersSchool") private String sfathersSchool;
    @Column(name = "sfathersEducationLevel") private String sfathersEducationLevel;
    @Column(name = "sfathersProfession") private String sfathersProfession;
    @Column(name = "sfathersTelephone") private Integer sfathersTelephone;
    @Column(name = "sfathersWhatsapp") private Integer sfathersWhatsapp;

    public NonacademicStaffEntity() {
    }

    public NonacademicStaffEntity(Long id, Long teacherNumber, String firstAppointDate, String nameInFull, String nameWithInitials, String usingName, String appointmentDateAsTemperary, String appointmentDateAsEpf, String dateOfBirth, String address, Integer telephone, Integer whatsapp, String emailAddress, String nic, String schoolStudied, String highestEducationQualification, String professionalQualification, String otherQualification, String extraCurricularActivities, String subjectTeaching1, String marriedOrNot, String dateGotMarried, String statusOfMarriage, String nameOfTheSpouse, String spouseSchool, String spouseHighestEducationLevel, Integer contactNumber, String occupation, String numberOfChildren, String educationQualification, String universityOrInstitute, String year, String subject, String mothersName, String mothersSchool, String mothersEducationLevel, String mothersProfession, Integer mothersTelephone, Integer mothersWhatsapp, String fathersName, String fathersSchool, String fathersEducationLevel, String fathersProfession, Integer fathersTelephone, Integer fathersWhatsapp, String smothersName, String smothersSchool, String smothersEducationLevel, String smothersProfession, Integer smothersTelephone, Integer smothersWhatsapp, String sfathersName, String sfathersSchool, String sfathersEducationLevel, String sfathersProfession, Integer sfathersTelephone, Integer sfathersWhatsapp) {
        this.id = id;
        this.teacherNumber = teacherNumber;
        this.firstAppointDate = firstAppointDate;
        this.nameInFull = nameInFull;
        this.nameWithInitials = nameWithInitials;
        this.usingName = usingName;
        this.appointmentDateAsTemperary = appointmentDateAsTemperary;
        this.appointmentDateAsEpf = appointmentDateAsEpf;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.telephone = telephone;
        this.whatsapp = whatsapp;
        this.emailAddress = emailAddress;
        this.nic = nic;
        this.schoolStudied = schoolStudied;
        this.highestEducationQualification = highestEducationQualification;
        this.professionalQualification = professionalQualification;
        this.otherQualification = otherQualification;
        this.extraCurricularActivities = extraCurricularActivities;
        this.subjectTeaching1 = subjectTeaching1;
        this.marriedOrNot = marriedOrNot;
        this.dateGotMarried = dateGotMarried;
        this.statusOfMarriage = statusOfMarriage;
        this.nameOfTheSpouse = nameOfTheSpouse;
        this.spouseSchool = spouseSchool;
        this.spouseHighestEducationLevel = spouseHighestEducationLevel;
        this.contactNumber = contactNumber;
        this.occupation = occupation;
        this.numberOfChildren = numberOfChildren;
        this.educationQualification = educationQualification;
        this.universityOrInstitute = universityOrInstitute;
        this.year = year;
        this.subject = subject;
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
        this.smothersName = smothersName;
        this.smothersSchool = smothersSchool;
        this.smothersEducationLevel = smothersEducationLevel;
        this.smothersProfession = smothersProfession;
        this.smothersTelephone = smothersTelephone;
        this.smothersWhatsapp = smothersWhatsapp;
        this.sfathersName = sfathersName;
        this.sfathersSchool = sfathersSchool;
        this.sfathersEducationLevel = sfathersEducationLevel;
        this.sfathersProfession = sfathersProfession;
        this.sfathersTelephone = sfathersTelephone;
        this.sfathersWhatsapp = sfathersWhatsapp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(Long teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getFirstAppointDate() {
        return firstAppointDate;
    }

    public void setFirstAppointDate(String firstAppointDate) {
        this.firstAppointDate = firstAppointDate;
    }

    public String getNameInFull() {
        return nameInFull;
    }

    public void setNameInFull(String nameInFull) {
        this.nameInFull = nameInFull;
    }

    public String getNameWithInitials() {
        return nameWithInitials;
    }

    public void setNameWithInitials(String nameWithInitials) {
        this.nameWithInitials = nameWithInitials;
    }

    public String getUsingName() {
        return usingName;
    }

    public void setUsingName(String usingName) {
        this.usingName = usingName;
    }

    public String getAppointmentDateAsTemperary() {
        return appointmentDateAsTemperary;
    }

    public void setAppointmentDateAsTemperary(String appointmentDateAsTemperary) {
        this.appointmentDateAsTemperary = appointmentDateAsTemperary;
    }

    public String getAppointmentDateAsEpf() {
        return appointmentDateAsEpf;
    }

    public void setAppointmentDateAsEpf(String appointmentDateAsEpf) {
        this.appointmentDateAsEpf = appointmentDateAsEpf;
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

    public String getHighestEducationQualification() {
        return highestEducationQualification;
    }

    public void setHighestEducationQualification(String highestEducationQualification) {
        this.highestEducationQualification = highestEducationQualification;
    }

    public String getProfessionalQualification() {
        return professionalQualification;
    }

    public void setProfessionalQualification(String professionalQualification) {
        this.professionalQualification = professionalQualification;
    }

    public String getOtherQualification() {
        return otherQualification;
    }

    public void setOtherQualification(String otherQualification) {
        this.otherQualification = otherQualification;
    }

    public String getExtraCurricularActivities() {
        return extraCurricularActivities;
    }

    public void setExtraCurricularActivities(String extraCurricularActivities) {
        this.extraCurricularActivities = extraCurricularActivities;
    }

    public String getSubjectTeaching1() {
        return subjectTeaching1;
    }

    public void setSubjectTeaching1(String subjectTeaching1) {
        this.subjectTeaching1 = subjectTeaching1;
    }

    public String getMarriedOrNot() {
        return marriedOrNot;
    }

    public void setMarriedOrNot(String marriedOrNot) {
        this.marriedOrNot = marriedOrNot;
    }

    public String getDateGotMarried() {
        return dateGotMarried;
    }

    public void setDateGotMarried(String dateGotMarried) {
        this.dateGotMarried = dateGotMarried;
    }

    public String getStatusOfMarriage() {
        return statusOfMarriage;
    }

    public void setStatusOfMarriage(String statusOfMarriage) {
        this.statusOfMarriage = statusOfMarriage;
    }

    public String getNameOfTheSpouse() {
        return nameOfTheSpouse;
    }

    public void setNameOfTheSpouse(String nameOfTheSpouse) {
        this.nameOfTheSpouse = nameOfTheSpouse;
    }

    public String getSpouseSchool() {
        return spouseSchool;
    }

    public void setSpouseSchool(String spouseSchool) {
        this.spouseSchool = spouseSchool;
    }

    public String getSpouseHighestEducationLevel() {
        return spouseHighestEducationLevel;
    }

    public void setSpouseHighestEducationLevel(String spouseHighestEducationLevel) {
        this.spouseHighestEducationLevel = spouseHighestEducationLevel;
    }

    public Integer getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getEducationQualification() {
        return educationQualification;
    }

    public void setEducationQualification(String educationQualification) {
        this.educationQualification = educationQualification;
    }

    public String getUniversityOrInstitute() {
        return universityOrInstitute;
    }

    public void setUniversityOrInstitute(String universityOrInstitute) {
        this.universityOrInstitute = universityOrInstitute;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public String getSmothersName() {
        return smothersName;
    }

    public void setSmothersName(String smothersName) {
        this.smothersName = smothersName;
    }

    public String getSmothersSchool() {
        return smothersSchool;
    }

    public void setSmothersSchool(String smothersSchool) {
        this.smothersSchool = smothersSchool;
    }

    public String getSmothersEducationLevel() {
        return smothersEducationLevel;
    }

    public void setSmothersEducationLevel(String smothersEducationLevel) {
        this.smothersEducationLevel = smothersEducationLevel;
    }

    public String getSmothersProfession() {
        return smothersProfession;
    }

    public void setSmothersProfession(String smothersProfession) {
        this.smothersProfession = smothersProfession;
    }

    public Integer getSmothersTelephone() {
        return smothersTelephone;
    }

    public void setSmothersTelephone(Integer smothersTelephone) {
        this.smothersTelephone = smothersTelephone;
    }

    public Integer getSmothersWhatsapp() {
        return smothersWhatsapp;
    }

    public void setSmothersWhatsapp(Integer smothersWhatsapp) {
        this.smothersWhatsapp = smothersWhatsapp;
    }

    public String getSfathersName() {
        return sfathersName;
    }

    public void setSfathersName(String sfathersName) {
        this.sfathersName = sfathersName;
    }

    public String getSfathersSchool() {
        return sfathersSchool;
    }

    public void setSfathersSchool(String sfathersSchool) {
        this.sfathersSchool = sfathersSchool;
    }

    public String getSfathersEducationLevel() {
        return sfathersEducationLevel;
    }

    public void setSfathersEducationLevel(String sfathersEducationLevel) {
        this.sfathersEducationLevel = sfathersEducationLevel;
    }

    public String getSfathersProfession() {
        return sfathersProfession;
    }

    public void setSfathersProfession(String sfathersProfession) {
        this.sfathersProfession = sfathersProfession;
    }

    public Integer getSfathersTelephone() {
        return sfathersTelephone;
    }

    public void setSfathersTelephone(Integer sfathersTelephone) {
        this.sfathersTelephone = sfathersTelephone;
    }

    public Integer getSfathersWhatsapp() {
        return sfathersWhatsapp;
    }

    public void setSfathersWhatsapp(Integer sfathersWhatsapp) {
        this.sfathersWhatsapp = sfathersWhatsapp;
    }
}

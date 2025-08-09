// CertificationRequestDto.java
package com.bit.backend.dtos;

import java.time.LocalDateTime;

public class CertificationRequestDto {

    private Long id;
    private String studentAdmissionNumber;
    private String studentName;
    private Boolean paymentsCleared;
    private Boolean libraryBooksReturned;
    private Boolean scienceLabCleared;
    private Boolean ictLabCleared;
    private Boolean sportsItemsReturned;
    private LocalDateTime requestDate;
    private String status;
    private String approvedBy;
    private LocalDateTime approvalDate;
    private String remarks;
    private String type; // NEW FIELD ADDED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CertificationRequestDto() {}

    public CertificationRequestDto(Long id, String studentAdmissionNumber, String studentName,
                                   Boolean paymentsCleared, Boolean libraryBooksReturned,
                                   Boolean scienceLabCleared, Boolean ictLabCleared,
                                   Boolean sportsItemsReturned, LocalDateTime requestDate,
                                   String status, String approvedBy, LocalDateTime approvalDate,
                                   String remarks, String type, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.studentAdmissionNumber = studentAdmissionNumber;
        this.studentName = studentName;
        this.paymentsCleared = paymentsCleared;
        this.libraryBooksReturned = libraryBooksReturned;
        this.scienceLabCleared = scienceLabCleared;
        this.ictLabCleared = ictLabCleared;
        this.sportsItemsReturned = sportsItemsReturned;
        this.requestDate = requestDate;
        this.status = status;
        this.approvedBy = approvedBy;
        this.approvalDate = approvalDate;
        this.remarks = remarks;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentAdmissionNumber() { return studentAdmissionNumber; }
    public void setStudentAdmissionNumber(String studentAdmissionNumber) { this.studentAdmissionNumber = studentAdmissionNumber; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Boolean getPaymentsCleared() { return paymentsCleared; }
    public void setPaymentsCleared(Boolean paymentsCleared) { this.paymentsCleared = paymentsCleared; }

    public Boolean getLibraryBooksReturned() { return libraryBooksReturned; }
    public void setLibraryBooksReturned(Boolean libraryBooksReturned) { this.libraryBooksReturned = libraryBooksReturned; }

    public Boolean getScienceLabCleared() { return scienceLabCleared; }
    public void setScienceLabCleared(Boolean scienceLabCleared) { this.scienceLabCleared = scienceLabCleared; }

    public Boolean getIctLabCleared() { return ictLabCleared; }
    public void setIctLabCleared(Boolean ictLabCleared) { this.ictLabCleared = ictLabCleared; }

    public Boolean getSportsItemsReturned() { return sportsItemsReturned; }
    public void setSportsItemsReturned(Boolean sportsItemsReturned) { this.sportsItemsReturned = sportsItemsReturned; }

    public LocalDateTime getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }

    public LocalDateTime getApprovalDate() { return approvalDate; }
    public void setApprovalDate(LocalDateTime approvalDate) { this.approvalDate = approvalDate; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
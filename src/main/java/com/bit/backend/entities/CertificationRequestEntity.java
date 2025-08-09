// CertificationRequestEntity.java
package com.bit.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "certification_request")
public class CertificationRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_admission_number")
    private String studentAdmissionNumber;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "payments_cleared")
    private Boolean paymentsCleared;

    @Column(name = "library_books_returned")
    private Boolean libraryBooksReturned;

    @Column(name = "science_lab_cleared")
    private Boolean scienceLabCleared;

    @Column(name = "ict_lab_cleared")
    private Boolean ictLabCleared;

    @Column(name = "sports_items_returned")
    private Boolean sportsItemsReturned;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "status")
    private String status; // PENDING, APPROVED, REJECTED, COMPLETED

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "type")
    private String type;

    public CertificationRequestEntity() {}

    public CertificationRequestEntity(Long id, String studentAdmissionNumber, String studentName,
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

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (requestDate == null) {
            requestDate = LocalDateTime.now();
        }
        if (status == null) {
            status = "PENDING";
        }
        if (type == null || type.trim().isEmpty()) {
            type = "GENERAL";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
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
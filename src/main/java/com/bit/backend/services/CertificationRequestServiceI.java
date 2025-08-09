// CertificationRequestServiceI.java
package com.bit.backend.services;

import com.bit.backend.dtos.CertificationRequestDto;

import java.util.List;
import java.util.Map;

public interface CertificationRequestServiceI {

    CertificationRequestDto addCertificationRequest(CertificationRequestDto certificationRequestDto);
    List<CertificationRequestDto> getAllCertificationRequests();
    CertificationRequestDto getCertificationRequestById(Long id);
    CertificationRequestDto updateCertificationRequest(Long id, CertificationRequestDto certificationRequestDto);
    CertificationRequestDto deleteCertificationRequest(Long id);

    // Existing business methods
    List<CertificationRequestDto> getRequestsByStudentAdmissionNumber(String admissionNumber);
    List<CertificationRequestDto> getRequestsByStatus(String status);
    List<CertificationRequestDto> searchRequestsByStudentName(String studentName);
    CertificationRequestDto approveRequest(Long id, String approvedBy, String remarks);
    CertificationRequestDto rejectRequest(Long id, String rejectedBy, String remarks);
    boolean hasPendingRequest(String admissionNumber);
    Map<String, Object> getRequestStatistics();
    List<CertificationRequestDto> getCompletedClearances();
    List<CertificationRequestDto> getIncompleteClearances();

    // NEW: Type-based methods
    List<CertificationRequestDto> getRequestsByType(String type);
    List<CertificationRequestDto> getRequestsByStatusAndType(String status, String type);
    List<CertificationRequestDto> searchRequestsByStudentNameAndType(String studentName, String type);
    boolean hasPendingRequestByType(String admissionNumber, String type);
    List<CertificationRequestDto> getCompletedClearancesByType(String type);
    List<CertificationRequestDto> getIncompleteClearancesByType(String type);
    List<String> getAllCertificationTypes();
    Map<String, Object> getStatisticsByType(String type);
    Map<String, Long> getRequestCountByType();
}
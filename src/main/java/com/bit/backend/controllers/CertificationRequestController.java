// CertificationRequestController.java
package com.bit.backend.controllers;

import com.bit.backend.dtos.CertificationRequestDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.CertificationRequestServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class CertificationRequestController {

    private final CertificationRequestServiceI certificationRequestServiceI;

    public CertificationRequestController(CertificationRequestServiceI certificationRequestServiceI) {
        this.certificationRequestServiceI = certificationRequestServiceI;
    }

    @PostMapping("/certification-request")
    public ResponseEntity<CertificationRequestDto> addCertificationRequest(@RequestBody CertificationRequestDto certificationRequestDto) {
        try {
            CertificationRequestDto savedRequest = certificationRequestServiceI.addCertificationRequest(certificationRequestDto);
            return ResponseEntity.created(URI.create("/certification-request/" + savedRequest.getId())).body(savedRequest);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request")
    public ResponseEntity<List<CertificationRequestDto>> getAllCertificationRequests() {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.getAllCertificationRequests();
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/{id}")
    public ResponseEntity<CertificationRequestDto> getCertificationRequestById(@PathVariable Long id) {
        try {
            CertificationRequestDto request = certificationRequestServiceI.getCertificationRequestById(id);
            return ResponseEntity.ok(request);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/certification-request/{id}")
    public ResponseEntity<CertificationRequestDto> updateCertificationRequest(@PathVariable Long id, @RequestBody CertificationRequestDto certificationRequestDto) {
        try {
            CertificationRequestDto updatedRequest = certificationRequestServiceI.updateCertificationRequest(id, certificationRequestDto);
            return ResponseEntity.ok(updatedRequest);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/certification-request/{id}")
    public ResponseEntity<CertificationRequestDto> deleteCertificationRequest(@PathVariable Long id) {
        try {
            CertificationRequestDto deletedRequest = certificationRequestServiceI.deleteCertificationRequest(id);
            return ResponseEntity.ok(deletedRequest);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // SEARCH AND FILTER ENDPOINTS

    @GetMapping("/certification-request/student/{admissionNumber}")
    public ResponseEntity<List<CertificationRequestDto>> getRequestsByStudentAdmissionNumber(@PathVariable String admissionNumber) {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.getRequestsByStudentAdmissionNumber(admissionNumber);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/status/{status}")
    public ResponseEntity<List<CertificationRequestDto>> getRequestsByStatus(@PathVariable String status) {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.getRequestsByStatus(status);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/search")
    public ResponseEntity<List<CertificationRequestDto>> searchRequestsByStudentName(@RequestParam String studentName) {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.searchRequestsByStudentName(studentName);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // NEW TYPE-BASED ENDPOINTS

    @GetMapping("/certification-request/type/{type}")
    public ResponseEntity<List<CertificationRequestDto>> getRequestsByType(@PathVariable String type) {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.getRequestsByType(type);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/status/{status}/type/{type}")
    public ResponseEntity<List<CertificationRequestDto>> getRequestsByStatusAndType(@PathVariable String status, @PathVariable String type) {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.getRequestsByStatusAndType(status, type);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/search/type/{type}")
    public ResponseEntity<List<CertificationRequestDto>> searchRequestsByStudentNameAndType(
            @RequestParam String studentName, @PathVariable String type) {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.searchRequestsByStudentNameAndType(studentName, type);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/completed-clearances/type/{type}")
    public ResponseEntity<List<CertificationRequestDto>> getCompletedClearancesByType(@PathVariable String type) {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.getCompletedClearancesByType(type);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/incomplete-clearances/type/{type}")
    public ResponseEntity<List<CertificationRequestDto>> getIncompleteClearancesByType(@PathVariable String type) {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.getIncompleteClearancesByType(type);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/types")
    public ResponseEntity<List<String>> getAllCertificationTypes() {
        try {
            List<String> types = certificationRequestServiceI.getAllCertificationTypes();
            return ResponseEntity.ok(types);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/statistics/type/{type}")
    public ResponseEntity<Map<String, Object>> getStatisticsByType(@PathVariable String type) {
        try {
            Map<String, Object> stats = certificationRequestServiceI.getStatisticsByType(type);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/count-by-type")
    public ResponseEntity<Map<String, Long>> getRequestCountByType() {
        try {
            Map<String, Long> counts = certificationRequestServiceI.getRequestCountByType();
            return ResponseEntity.ok(counts);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // APPROVAL ENDPOINTS

    @PutMapping("/certification-request/{id}/approve")
    public ResponseEntity<CertificationRequestDto> approveRequest(@PathVariable Long id,
                                                                  @RequestParam String approvedBy,
                                                                  @RequestParam(required = false) String remarks) {
        try {
            CertificationRequestDto approvedRequest = certificationRequestServiceI.approveRequest(id, approvedBy, remarks);
            return ResponseEntity.ok(approvedRequest);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/certification-request/{id}/reject")
    public ResponseEntity<CertificationRequestDto> rejectRequest(@PathVariable Long id,
                                                                 @RequestParam String rejectedBy,
                                                                 @RequestParam(required = false) String remarks) {
        try {
            CertificationRequestDto rejectedRequest = certificationRequestServiceI.rejectRequest(id, rejectedBy, remarks);
            return ResponseEntity.ok(rejectedRequest);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UTILITY ENDPOINTS

    @GetMapping("/certification-request/check-pending/{admissionNumber}")
    public ResponseEntity<Boolean> checkPendingRequest(@PathVariable String admissionNumber) {
        try {
            boolean hasPending = certificationRequestServiceI.hasPendingRequest(admissionNumber);
            return ResponseEntity.ok(hasPending);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/check-pending/{admissionNumber}/type/{type}")
    public ResponseEntity<Boolean> checkPendingRequestByType(@PathVariable String admissionNumber, @PathVariable String type) {
        try {
            boolean hasPending = certificationRequestServiceI.hasPendingRequestByType(admissionNumber, type);
            return ResponseEntity.ok(hasPending);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/statistics")
    public ResponseEntity<Map<String, Object>> getRequestStatistics() {
        try {
            Map<String, Object> stats = certificationRequestServiceI.getRequestStatistics();
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/completed-clearances")
    public ResponseEntity<List<CertificationRequestDto>> getCompletedClearances() {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.getCompletedClearances();
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/certification-request/incomplete-clearances")
    public ResponseEntity<List<CertificationRequestDto>> getIncompleteClearances() {
        try {
            List<CertificationRequestDto> requests = certificationRequestServiceI.getIncompleteClearances();
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
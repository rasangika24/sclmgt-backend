// CertificationRequestService.java
package com.bit.backend.services.impl;

import com.bit.backend.dtos.CertificationRequestDto;
import com.bit.backend.entities.CertificationRequestEntity;
import com.bit.backend.entities.StudentEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.CertificationRequestMapper;
import com.bit.backend.repositories.CertificationRequestRepository;
import com.bit.backend.repositories.StudentRepository;
import com.bit.backend.services.CertificationRequestServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CertificationRequestService implements CertificationRequestServiceI {

    private final CertificationRequestRepository certificationRequestRepository;
    private final StudentRepository studentRepository;
    private final CertificationRequestMapper certificationRequestMapper;

    public CertificationRequestService(CertificationRequestRepository certificationRequestRepository,
                                       StudentRepository studentRepository,
                                       CertificationRequestMapper certificationRequestMapper) {
        this.certificationRequestRepository = certificationRequestRepository;
        this.studentRepository = studentRepository;
        this.certificationRequestMapper = certificationRequestMapper;
    }

    @Override
    public CertificationRequestDto addCertificationRequest(CertificationRequestDto certificationRequestDto) {
        try {
            // Validate student admission number
            if (certificationRequestDto.getStudentAdmissionNumber() == null ||
                    certificationRequestDto.getStudentAdmissionNumber().trim().isEmpty()) {
                throw new AppException("Student admission number is required", HttpStatus.BAD_REQUEST);
            }

            // Check if student exists
            Optional<StudentEntity> studentEntity = studentRepository
                    .findByAdmissionNumber(certificationRequestDto.getStudentAdmissionNumber());

            if (!studentEntity.isPresent()) {
                throw new AppException("Student not found with admission number: " +
                        certificationRequestDto.getStudentAdmissionNumber(), HttpStatus.NOT_FOUND);
            }

            // Set student name from student entity
            certificationRequestDto.setStudentName(studentEntity.get().getNameinFull());

            // Check if student already has a pending request of the same type
            if (certificationRequestDto.getType() != null &&
                    hasPendingRequestByType(certificationRequestDto.getStudentAdmissionNumber(), certificationRequestDto.getType())) {
                throw new AppException("Student already has a pending certification request of type: " +
                        certificationRequestDto.getType(), HttpStatus.CONFLICT);
            }

            // Set default values
            if (certificationRequestDto.getPaymentsCleared() == null) {
                certificationRequestDto.setPaymentsCleared(false);
            }
            if (certificationRequestDto.getLibraryBooksReturned() == null) {
                certificationRequestDto.setLibraryBooksReturned(false);
            }
            if (certificationRequestDto.getScienceLabCleared() == null) {
                certificationRequestDto.setScienceLabCleared(false);
            }
            if (certificationRequestDto.getIctLabCleared() == null) {
                certificationRequestDto.setIctLabCleared(false);
            }
            if (certificationRequestDto.getSportsItemsReturned() == null) {
                certificationRequestDto.setSportsItemsReturned(false);
            }

            // Set default type if not provided
            if (certificationRequestDto.getType() == null || certificationRequestDto.getType().trim().isEmpty()) {
                certificationRequestDto.setType("GENERAL");
            }

            CertificationRequestEntity certificationRequestEntity =
                    certificationRequestMapper.toCertificationRequestEntity(certificationRequestDto);
            CertificationRequestEntity savedEntity = certificationRequestRepository.save(certificationRequestEntity);

            return certificationRequestMapper.toCertificationRequestDto(savedEntity);

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CertificationRequestDto> getAllCertificationRequests() {
        try {
            List<CertificationRequestEntity> entities = certificationRequestRepository.findAll();
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CertificationRequestDto getCertificationRequestById(Long id) {
        try {
            Optional<CertificationRequestEntity> optionalEntity = certificationRequestRepository.findById(id);
            if (!optionalEntity.isPresent()) {
                throw new AppException("Certification request not found", HttpStatus.NOT_FOUND);
            }
            return certificationRequestMapper.toCertificationRequestDto(optionalEntity.get());
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CertificationRequestDto updateCertificationRequest(Long id, CertificationRequestDto certificationRequestDto) {
        try {
            Optional<CertificationRequestEntity> optionalEntity = certificationRequestRepository.findById(id);
            if (!optionalEntity.isPresent()) {
                throw new AppException("Certification request not found", HttpStatus.NOT_FOUND);
            }

            CertificationRequestEntity existingEntity = optionalEntity.get();
            CertificationRequestEntity updatedEntity = certificationRequestMapper.toCertificationRequestEntity(certificationRequestDto);
            updatedEntity.setId(id);
            updatedEntity.setCreatedAt(existingEntity.getCreatedAt()); // Preserve creation date

            CertificationRequestEntity savedEntity = certificationRequestRepository.save(updatedEntity);
            return certificationRequestMapper.toCertificationRequestDto(savedEntity);

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CertificationRequestDto deleteCertificationRequest(Long id) {
        try {
            Optional<CertificationRequestEntity> optionalEntity = certificationRequestRepository.findById(id);
            if (!optionalEntity.isPresent()) {
                throw new AppException("Certification request not found", HttpStatus.NOT_FOUND);
            }

            certificationRequestRepository.deleteById(id);
            return certificationRequestMapper.toCertificationRequestDto(optionalEntity.get());

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CertificationRequestDto> getRequestsByStudentAdmissionNumber(String admissionNumber) {
        try {
            List<CertificationRequestEntity> entities =
                    certificationRequestRepository.findByStudentAdmissionNumber(admissionNumber);
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CertificationRequestDto> getRequestsByStatus(String status) {
        try {
            List<CertificationRequestEntity> entities =
                    certificationRequestRepository.findByStatusOrderByRequestDateDesc(status);
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CertificationRequestDto> searchRequestsByStudentName(String studentName) {
        try {
            List<CertificationRequestEntity> entities =
                    certificationRequestRepository.findByStudentNameContainingIgnoreCaseOrderByRequestDateDesc(studentName);
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CertificationRequestDto approveRequest(Long id, String approvedBy, String remarks) {
        try {
            Optional<CertificationRequestEntity> optionalEntity = certificationRequestRepository.findById(id);
            if (!optionalEntity.isPresent()) {
                throw new AppException("Certification request not found", HttpStatus.NOT_FOUND);
            }

            CertificationRequestEntity entity = optionalEntity.get();
            entity.setStatus("APPROVED");
            entity.setApprovedBy(approvedBy);
            entity.setApprovalDate(LocalDateTime.now());
            entity.setRemarks(remarks);

            CertificationRequestEntity savedEntity = certificationRequestRepository.save(entity);
            return certificationRequestMapper.toCertificationRequestDto(savedEntity);

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CertificationRequestDto rejectRequest(Long id, String rejectedBy, String remarks) {
        try {
            Optional<CertificationRequestEntity> optionalEntity = certificationRequestRepository.findById(id);
            if (!optionalEntity.isPresent()) {
                throw new AppException("Certification request not found", HttpStatus.NOT_FOUND);
            }

            CertificationRequestEntity entity = optionalEntity.get();
            entity.setStatus("REJECTED");
            entity.setApprovedBy(rejectedBy);
            entity.setApprovalDate(LocalDateTime.now());
            entity.setRemarks(remarks);

            CertificationRequestEntity savedEntity = certificationRequestRepository.save(entity);
            return certificationRequestMapper.toCertificationRequestDto(savedEntity);

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean hasPendingRequest(String admissionNumber) {
        try {
            return certificationRequestRepository.hasPendingRequest(admissionNumber);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Map<String, Object> getRequestStatistics() {
        try {
            Map<String, Object> stats = new HashMap<>();

            long totalRequests = certificationRequestRepository.count();
            long pendingRequests = certificationRequestRepository.countPendingRequests();

            List<String> approvedStatuses = Arrays.asList("APPROVED", "COMPLETED");
            List<CertificationRequestEntity> approvedRequests =
                    certificationRequestRepository.findByStatusIn(approvedStatuses);

            List<CertificationRequestEntity> rejectedRequests =
                    certificationRequestRepository.findByStatusOrderByRequestDateDesc("REJECTED");

            List<CertificationRequestEntity> completedClearances =
                    certificationRequestRepository.findCompletedClearances();

            List<CertificationRequestEntity> incompleteClearances =
                    certificationRequestRepository.findIncompleteClearances();

            stats.put("totalRequests", totalRequests);
            stats.put("pendingRequests", pendingRequests);
            stats.put("approvedRequests", approvedRequests.size());
            stats.put("rejectedRequests", rejectedRequests.size());
            stats.put("completedClearances", completedClearances.size());
            stats.put("incompleteClearances", incompleteClearances.size());

            if (totalRequests > 0) {
                stats.put("approvalRate", (double) approvedRequests.size() / totalRequests * 100);
                stats.put("completionRate", (double) completedClearances.size() / totalRequests * 100);
            } else {
                stats.put("approvalRate", 0.0);
                stats.put("completionRate", 0.0);
            }

            // Add type-based statistics
            Map<String, Long> typeStats = getRequestCountByType();
            stats.put("requestsByType", typeStats);

            return stats;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CertificationRequestDto> getCompletedClearances() {
        try {
            List<CertificationRequestEntity> entities = certificationRequestRepository.findCompletedClearances();
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CertificationRequestDto> getIncompleteClearances() {
        try {
            List<CertificationRequestEntity> entities = certificationRequestRepository.findIncompleteClearances();
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // NEW TYPE-BASED METHODS

    @Override
    public List<CertificationRequestDto> getRequestsByType(String type) {
        try {
            List<CertificationRequestEntity> entities =
                    certificationRequestRepository.findByTypeOrderByRequestDateDesc(type);
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CertificationRequestDto> getRequestsByStatusAndType(String status, String type) {
        try {
            List<CertificationRequestEntity> entities =
                    certificationRequestRepository.findByStatusAndTypeOrderByRequestDateDesc(status, type);
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CertificationRequestDto> searchRequestsByStudentNameAndType(String studentName, String type) {
        try {
            List<CertificationRequestEntity> entities =
                    certificationRequestRepository.findByStudentNameContainingIgnoreCaseAndTypeOrderByRequestDateDesc(studentName, type);
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean hasPendingRequestByType(String admissionNumber, String type) {
        try {
            return certificationRequestRepository.hasPendingRequestByType(admissionNumber, type);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<CertificationRequestDto> getCompletedClearancesByType(String type) {
        try {
            List<CertificationRequestEntity> entities = certificationRequestRepository.findCompletedClearancesByType(type);
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<CertificationRequestDto> getIncompleteClearancesByType(String type) {
        try {
            List<CertificationRequestEntity> entities = certificationRequestRepository.findIncompleteClearancesByType(type);
            return certificationRequestMapper.toCertificationRequestDtoList(entities);
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<String> getAllCertificationTypes() {
        try {
            return certificationRequestRepository.findDistinctTypes();
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Map<String, Object> getStatisticsByType(String type) {
        try {
            Map<String, Object> stats = new HashMap<>();

            long totalRequests = certificationRequestRepository.findByTypeOrderByRequestDateDesc(type).size();
            long pendingRequests = certificationRequestRepository.countPendingRequestsByType(type);

            List<String> approvedStatuses = Arrays.asList("APPROVED", "COMPLETED");
            List<CertificationRequestEntity> approvedRequests =
                    certificationRequestRepository.findByStatusInAndType(approvedStatuses, type);

            List<CertificationRequestEntity> rejectedRequests =
                    certificationRequestRepository.findByStatusAndTypeOrderByRequestDateDesc("REJECTED", type);

            List<CertificationRequestEntity> completedClearances =
                    certificationRequestRepository.findCompletedClearancesByType(type);

            List<CertificationRequestEntity> incompleteClearances =
                    certificationRequestRepository.findIncompleteClearancesByType(type);

            stats.put("totalRequests", totalRequests);
            stats.put("pendingRequests", pendingRequests);
            stats.put("approvedRequests", approvedRequests.size());
            stats.put("rejectedRequests", rejectedRequests.size());
            stats.put("completedClearances", completedClearances.size());
            stats.put("incompleteClearances", incompleteClearances.size());
            stats.put("type", type);

            if (totalRequests > 0) {
                stats.put("approvalRate", (double) approvedRequests.size() / totalRequests * 100);
                stats.put("completionRate", (double) completedClearances.size() / totalRequests * 100);
            } else {
                stats.put("approvalRate", 0.0);
                stats.put("completionRate", 0.0);
            }

            return stats;

        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Map<String, Long> getRequestCountByType() {
        try {
            List<Object[]> results = certificationRequestRepository.getCountByType();
            return results.stream()
                    .collect(Collectors.toMap(
                            result -> (String) result[0],
                            result -> (Long) result[1]
                    ));
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
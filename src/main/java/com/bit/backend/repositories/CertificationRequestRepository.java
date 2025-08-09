// CertificationRequestRepository.java
package com.bit.backend.repositories;

import com.bit.backend.entities.CertificationRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CertificationRequestRepository extends JpaRepository<CertificationRequestEntity, Long> {

    // Find by student admission number
    List<CertificationRequestEntity> findByStudentAdmissionNumber(String studentAdmissionNumber);

    // Find by status
    List<CertificationRequestEntity> findByStatusOrderByRequestDateDesc(String status);

    // NEW: Find by type
    List<CertificationRequestEntity> findByTypeOrderByRequestDateDesc(String type);

    // NEW: Find by status and type
    List<CertificationRequestEntity> findByStatusAndTypeOrderByRequestDateDesc(String status, String type);

    // Find by student name (case insensitive)
    List<CertificationRequestEntity> findByStudentNameContainingIgnoreCaseOrderByRequestDateDesc(String studentName);

    // NEW: Find by student name and type
    List<CertificationRequestEntity> findByStudentNameContainingIgnoreCaseAndTypeOrderByRequestDateDesc(String studentName, String type);

    // Check if student has pending requests
    @Query("SELECT COUNT(c) > 0 FROM CertificationRequestEntity c WHERE c.studentAdmissionNumber = :admissionNumber AND c.status = 'PENDING'")
    boolean hasPendingRequest(@Param("admissionNumber") String admissionNumber);

    // NEW: Check if student has pending requests of specific type
    @Query("SELECT COUNT(c) > 0 FROM CertificationRequestEntity c WHERE c.studentAdmissionNumber = :admissionNumber AND c.status = 'PENDING' AND c.type = :type")
    boolean hasPendingRequestByType(@Param("admissionNumber") String admissionNumber, @Param("type") String type);

    // Get all pending requests count
    @Query("SELECT COUNT(c) FROM CertificationRequestEntity c WHERE c.status = 'PENDING'")
    long countPendingRequests();

    // NEW: Get pending requests count by type
    @Query("SELECT COUNT(c) FROM CertificationRequestEntity c WHERE c.status = 'PENDING' AND c.type = :type")
    long countPendingRequestsByType(@Param("type") String type);

    // Get requests by approval status
    @Query("SELECT c FROM CertificationRequestEntity c WHERE c.status IN :statuses ORDER BY c.requestDate DESC")
    List<CertificationRequestEntity> findByStatusIn(@Param("statuses") List<String> statuses);

    // NEW: Get requests by approval status and type
    @Query("SELECT c FROM CertificationRequestEntity c WHERE c.status IN :statuses AND c.type = :type ORDER BY c.requestDate DESC")
    List<CertificationRequestEntity> findByStatusInAndType(@Param("statuses") List<String> statuses, @Param("type") String type);

    // Get completed requests (all clearances approved)
    @Query("SELECT c FROM CertificationRequestEntity c WHERE c.paymentsCleared = true AND c.libraryBooksReturned = true AND c.scienceLabCleared = true AND c.ictLabCleared = true AND c.sportsItemsReturned = true ORDER BY c.requestDate DESC")
    List<CertificationRequestEntity> findCompletedClearances();

    // NEW: Get completed requests by type
    @Query("SELECT c FROM CertificationRequestEntity c WHERE c.paymentsCleared = true AND c.libraryBooksReturned = true AND c.scienceLabCleared = true AND c.ictLabCleared = true AND c.sportsItemsReturned = true AND c.type = :type ORDER BY c.requestDate DESC")
    List<CertificationRequestEntity> findCompletedClearancesByType(@Param("type") String type);

    // Get incomplete requests (any clearance pending)
    @Query("SELECT c FROM CertificationRequestEntity c WHERE c.paymentsCleared = false OR c.libraryBooksReturned = false OR c.scienceLabCleared = false OR c.ictLabCleared = false OR c.sportsItemsReturned = false ORDER BY c.requestDate DESC")
    List<CertificationRequestEntity> findIncompleteClearances();

    // NEW: Get incomplete requests by type
    @Query("SELECT c FROM CertificationRequestEntity c WHERE (c.paymentsCleared = false OR c.libraryBooksReturned = false OR c.scienceLabCleared = false OR c.ictLabCleared = false OR c.sportsItemsReturned = false) AND c.type = :type ORDER BY c.requestDate DESC")
    List<CertificationRequestEntity> findIncompleteClearancesByType(@Param("type") String type);

    // NEW: Get distinct types
    @Query("SELECT DISTINCT c.type FROM CertificationRequestEntity c WHERE c.type IS NOT NULL ORDER BY c.type")
    List<String> findDistinctTypes();

    // NEW: Get statistics by type
    @Query("SELECT c.type, COUNT(c) FROM CertificationRequestEntity c WHERE c.type IS NOT NULL GROUP BY c.type")
    List<Object[]> getCountByType();
}
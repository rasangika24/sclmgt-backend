package com.bit.backend.repositories;

import com.bit.backend.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    boolean existsByNic(String nic);

    Optional<StudentEntity> findByAdmissionNumber(String admissionNumber);
}
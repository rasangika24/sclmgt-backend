package com.bit.backend.repositories;

import com.bit.backend.entities.GradeGenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradesRepository extends JpaRepository<GradeGenEntity, Long> {
}

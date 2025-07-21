package com.bit.backend.repositories;

import com.bit.backend.entities.AcademicStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicStaffRepository extends JpaRepository<AcademicStaffEntity, Long> {

    boolean existsByNic(String nic);

}

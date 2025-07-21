package com.bit.backend.repositories;

import com.bit.backend.entities.NonacademicStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonacademicStaffRepository extends JpaRepository<NonacademicStaffEntity, Long> {

    boolean existsByNic(String nic);

}

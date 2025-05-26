package com.bit.backend.repositories;

import com.bit.backend.entities.SchedulerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchedulerRepository extends JpaRepository<SchedulerEntity, Long> {

    Optional<List<SchedulerEntity>> findByEmpNo(String empNo);
}

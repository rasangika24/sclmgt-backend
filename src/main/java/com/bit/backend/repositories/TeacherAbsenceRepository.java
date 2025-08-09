package com.bit.backend.repositories;

import com.bit.backend.entities.TeacherAbsenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeacherAbsenceRepository extends JpaRepository<TeacherAbsenceEntity, Long> {

    List<TeacherAbsenceEntity> findByAbsenceDateAndStatus(LocalDate absenceDate, String status);

    List<TeacherAbsenceEntity> findByAbsenceDate(LocalDate absenceDate);

    Optional<TeacherAbsenceEntity> findByTeacherIdAndAbsenceDate(String teacherId, LocalDate absenceDate);

    List<TeacherAbsenceEntity> findByStatusOrderByAbsenceDateDesc(String status);
}
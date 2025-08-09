package com.bit.backend.repositories;

import com.bit.backend.entities.ReliefPeriodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReliefPeriodRepository extends JpaRepository<ReliefPeriodEntity, Long> {

    List<ReliefPeriodEntity> findByDateOrderByPeriodNumberAsc(LocalDate date);

    List<ReliefPeriodEntity> findByAbsentTeacherIdAndDateOrderByPeriodNumberAsc(String absentTeacherId, LocalDate date);

    List<ReliefPeriodEntity> findByReliefTeacherIdAndDateOrderByPeriodNumberAsc(String reliefTeacherId, LocalDate date);

    List<ReliefPeriodEntity> findByStatusOrderByDateDescPeriodNumberAsc(String status);

    @Query("SELECT r FROM ReliefPeriodEntity r WHERE r.date = :date AND r.periodNumber = :periodNumber AND r.reliefTeacherId = :teacherId")
    List<ReliefPeriodEntity> findConflicts(@Param("date") LocalDate date,
                                           @Param("periodNumber") Integer periodNumber,
                                           @Param("teacherId") String teacherId);

    @Query("SELECT r FROM ReliefPeriodEntity r WHERE r.date BETWEEN :startDate AND :endDate ORDER BY r.date DESC, r.periodNumber ASC")
    List<ReliefPeriodEntity> findByDateRange(@Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);
}
package com.bit.backend.repositories;

import com.bit.backend.entities.TimetableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TimetableRepository extends JpaRepository<TimetableEntity, Long> {

    // Your existing methods...
    List<TimetableEntity> findByTeacherIdAndStatusOrderByDayOfWeekAscPeriodNumberAsc(
            String teacherId, Integer status);

    List<TimetableEntity> findByTeacherIdAndAcademicYearAndStatusOrderByDayOfWeekAscPeriodNumberAsc(
            String teacherId, String academicYear, Integer status);

    List<TimetableEntity> findByTeacherIdAndAcademicYearAndTermAndStatusOrderByDayOfWeekAscPeriodNumberAsc(
            String teacherId, String academicYear, String term, Integer status);

    List<TimetableEntity> findByTeacherIdAndDayOfWeekAndStatusOrderByPeriodNumberAsc(
            String teacherId, String dayOfWeek, Integer status);

    @Query("SELECT t FROM TimetableEntity t WHERE t.teacherId = :teacherId " +
            "AND t.dayOfWeek = :dayOfWeek AND t.status = 1 " +
            "AND ((t.startTime <= :startTime AND t.endTime > :startTime) " +
            "OR (t.startTime < :endTime AND t.endTime >= :endTime) " +
            "OR (t.startTime >= :startTime AND t.endTime <= :endTime)) " +
            "AND (:id IS NULL OR t.id != :id)")
    List<TimetableEntity> findConflictingTimetables(
            @Param("teacherId") String teacherId,
            @Param("dayOfWeek") String dayOfWeek,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("id") Long id);

    List<TimetableEntity> findByAcademicYearAndTermAndStatusOrderByTeacherNameAscDayOfWeekAscPeriodNumberAsc(
            String academicYear, String term, Integer status);

    List<TimetableEntity> findByStatusOrderByTeacherNameAscDayOfWeekAscPeriodNumberAsc(Integer status);

    List<TimetableEntity> findByTeacherNameContainingIgnoreCaseAndStatusOrderByDayOfWeekAscPeriodNumberAsc(
            String teacherName, Integer status);

    List<TimetableEntity> findBySubjectContainingIgnoreCaseAndStatusOrderByTeacherNameAscDayOfWeekAscPeriodNumberAsc(
            String subject, Integer status);

    List<TimetableEntity> findByGradeAndStatusOrderByTeacherNameAscDayOfWeekAscPeriodNumberAsc(
            String grade, Integer status);

    // NEW METHODS for enhanced functionality
    List<TimetableEntity> findByClassRoomAndDayOfWeekAndStatusOrderByPeriodNumberAsc(
            String classRoom, String dayOfWeek, Integer status);

    // Get all timetables for a specific period across all teachers and days
    List<TimetableEntity> findByPeriodNumberAndStatusOrderByTeacherNameAscDayOfWeekAsc(
            Integer periodNumber, Integer status);

    // Get timetables by multiple subjects
    @Query("SELECT t FROM TimetableEntity t WHERE t.subject IN :subjects AND t.status = :status " +
            "ORDER BY t.teacherName ASC, t.dayOfWeek ASC, t.periodNumber ASC")
    List<TimetableEntity> findBySubjectInAndStatusOrderByTeacherNameAscDayOfWeekAscPeriodNumberAsc(
            @Param("subjects") List<String> subjects, @Param("status") Integer status);

    // Get timetables by multiple grades
    @Query("SELECT t FROM TimetableEntity t WHERE t.grade IN :grades AND t.status = :status " +
            "ORDER BY t.teacherName ASC, t.dayOfWeek ASC, t.periodNumber ASC")
    List<TimetableEntity> findByGradeInAndStatusOrderByTeacherNameAscDayOfWeekAscPeriodNumberAsc(
            @Param("grades") List<String> grades, @Param("status") Integer status);

    // Get teacher workload count
    @Query("SELECT COUNT(t) FROM TimetableEntity t WHERE t.teacherId = :teacherId AND t.status = 1")
    Long countByTeacherIdAndStatus(@Param("teacherId") String teacherId);

    // Get free periods count for a teacher on a specific day
    @Query("SELECT COUNT(t) FROM TimetableEntity t WHERE t.teacherId = :teacherId " +
            "AND t.dayOfWeek = :dayOfWeek AND t.status = 1")
    Long countByTeacherIdAndDayOfWeekAndStatus(@Param("teacherId") String teacherId,
                                               @Param("dayOfWeek") String dayOfWeek);


    @Query("SELECT t FROM TimetableEntity t WHERE t.dayOfWeek = :dayOfWeek " +
            "AND t.periodNumber = :periodNumber AND t.status = :status")
    List<TimetableEntity> findByDayOfWeekAndPeriodNumberAndStatus(
            @Param("dayOfWeek") String dayOfWeek,
            @Param("periodNumber") Integer periodNumber,
            @Param("status") Integer status);
}

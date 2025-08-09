package com.bit.backend.controllers;

import com.bit.backend.dtos.AvailableTeacherDto;
import com.bit.backend.dtos.ReliefPeriodDto;
import com.bit.backend.dtos.TeacherAbsenceDto;
import com.bit.backend.dtos.TimetableDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.ReliefPeriodServiceI;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relief-period")
public class ReliefPeriodController {

    private final ReliefPeriodServiceI reliefPeriodServiceI;

    public ReliefPeriodController(ReliefPeriodServiceI reliefPeriodServiceI) {
        this.reliefPeriodServiceI = reliefPeriodServiceI;
    }

    // Mark teacher absent
    @PostMapping("/absence")
    public ResponseEntity<TeacherAbsenceDto> markTeacherAbsent(@RequestBody TeacherAbsenceDto teacherAbsenceDto) {
        try {
            TeacherAbsenceDto saved = reliefPeriodServiceI.markTeacherAbsent(teacherAbsenceDto);
            return ResponseEntity.created(URI.create("/relief-period/absence/" + saved.getId())).body(saved);
        } catch (Exception e) {
            throw new AppException("Failed to mark teacher absent: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get absent teachers for a date
    @GetMapping("/absence/date/{date}")
    public ResponseEntity<List<TeacherAbsenceDto>> getAbsentTeachers(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<TeacherAbsenceDto> absences = reliefPeriodServiceI.getAbsentTeachers(date);
            return ResponseEntity.ok(absences);
        } catch (Exception e) {
            throw new AppException("Failed to get absent teachers: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all active absences
    @GetMapping("/absence/active")
    public ResponseEntity<List<TeacherAbsenceDto>> getAllActiveAbsences() {
        try {
            List<TeacherAbsenceDto> absences = reliefPeriodServiceI.getAllActiveAbsences();
            return ResponseEntity.ok(absences);
        } catch (Exception e) {
            throw new AppException("Failed to get active absences: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get timetable for absent teacher
    @GetMapping("/teacher/{teacherId}/timetable/{date}")
    public ResponseEntity<List<TimetableDto>> getAbsentTeacherTimetable(
            @PathVariable String teacherId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<TimetableDto> timetables = reliefPeriodServiceI.getAbsentTeacherTimetable(teacherId, date);
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            throw new AppException("Failed to get teacher timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get available teachers for a specific period
    @GetMapping("/available-teachers")
    public ResponseEntity<List<AvailableTeacherDto>> getAvailableTeachers(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Integer periodNumber) {
        try {
            List<AvailableTeacherDto> teachers = reliefPeriodServiceI.getAvailableTeachers(date, periodNumber);
            return ResponseEntity.ok(teachers);
        } catch (Exception e) {
            throw new AppException("Failed to get available teachers: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Assign relief teacher
    @PostMapping("/assign")
    public ResponseEntity<ReliefPeriodDto> assignReliefTeacher(@RequestBody ReliefPeriodDto reliefPeriodDto) {
        try {
            ReliefPeriodDto saved = reliefPeriodServiceI.assignReliefTeacher(reliefPeriodDto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            throw new AppException("Failed to assign relief teacher: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get relief periods for a date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<ReliefPeriodDto>> getReliefPeriods(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<ReliefPeriodDto> reliefPeriods = reliefPeriodServiceI.getReliefPeriods(date);
            return ResponseEntity.ok(reliefPeriods);
        } catch (Exception e) {
            throw new AppException("Failed to get relief periods: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get relief periods by teacher
    @GetMapping("/teacher/{teacherId}/date/{date}")
    public ResponseEntity<List<ReliefPeriodDto>> getReliefPeriodsByTeacher(
            @PathVariable String teacherId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<ReliefPeriodDto> reliefPeriods = reliefPeriodServiceI.getReliefPeriodsByTeacher(teacherId, date);
            return ResponseEntity.ok(reliefPeriods);
        } catch (Exception e) {
            throw new AppException("Failed to get relief periods by teacher: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update relief period
    @PutMapping("/{id}")
    public ResponseEntity<ReliefPeriodDto> updateReliefPeriod(
            @PathVariable Long id,
            @RequestBody ReliefPeriodDto reliefPeriodDto) {
        try {
            ReliefPeriodDto updated = reliefPeriodServiceI.updateReliefPeriod(id, reliefPeriodDto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            throw new AppException("Failed to update relief period: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Cancel relief period
    @PutMapping("/{id}/cancel")
    public ResponseEntity<ReliefPeriodDto> cancelReliefPeriod(@PathVariable Long id) {
        try {
            ReliefPeriodDto cancelled = reliefPeriodServiceI.cancelReliefPeriod(id);
            return ResponseEntity.ok(cancelled);
        } catch (Exception e) {
            throw new AppException("Failed to cancel relief period: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get relief period report
    @GetMapping("/report")
    public ResponseEntity<Map<String, Object>> getReliefPeriodReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            Map<String, Object> report = reliefPeriodServiceI.getReliefPeriodReport(startDate, endDate);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            throw new AppException("Failed to generate report: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
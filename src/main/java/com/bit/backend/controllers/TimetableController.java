package com.bit.backend.controllers;

import com.bit.backend.dtos.TimeSlotDto;
import com.bit.backend.dtos.TimetableDto;
import com.bit.backend.dtos.TimetableWeeklyDto;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.services.impl.TimetableService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/timetable")
public class TimetableController {

    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    // ENHANCED CRUD OPERATIONS

    @PostMapping
    public ResponseEntity<TimetableDto> addTimetable(@RequestBody TimetableDto timetableDto) {
        try {
            TimetableDto savedTimetable = timetableService.addTimetable(timetableDto);
            return ResponseEntity.created(URI.create("/timetable/" + savedTimetable.getId())).body(savedTimetable);
        } catch (Exception e) {
            throw new AppException("Failed to create timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // NEW ENHANCED ENDPOINTS

    // Get time slot information by period number
    @GetMapping("/time-slots/period/{periodNumber}")
    public ResponseEntity<TimeSlotDto> getTimeSlotByPeriod(@PathVariable Integer periodNumber) {
        try {
            TimeSlotDto timeSlot = timetableService.getTimeSlotByPeriod(periodNumber);
            if (timeSlot == null) {
                throw new AppException("Invalid period number", HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(timeSlot);
        } catch (Exception e) {
            throw new AppException("Failed to get time slot: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all standard time slots
    @GetMapping("/time-slots/all")
    public ResponseEntity<List<TimeSlotDto>> getAllStandardTimeSlots() {
        try {
            List<TimeSlotDto> timeSlots = timetableService.getAllStandardTimeSlots();
            return ResponseEntity.ok(timeSlots);
        } catch (Exception e) {
            throw new AppException("Failed to get time slots: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get free periods for a specific day
    @GetMapping("/teacher/{teacherId}/free-periods/{dayOfWeek}")
    public ResponseEntity<List<TimeSlotDto>> getFreePeriodsByDay(
            @PathVariable String teacherId, @PathVariable String dayOfWeek) {
        try {
            List<TimeSlotDto> freePeriods = timetableService.getFreePeriodsByDay(teacherId, dayOfWeek);
            return ResponseEntity.ok(freePeriods);
        } catch (Exception e) {
            throw new AppException("Failed to get free periods: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get free periods for entire week
    @GetMapping("/teacher/{teacherId}/free-periods/week")
    public ResponseEntity<Map<String, List<TimeSlotDto>>> getFreePeriodsWeek(@PathVariable String teacherId) {
        try {
            Map<String, List<TimeSlotDto>> weeklyFreePeriods = timetableService.getFreePeriodsWeek(teacherId);
            return ResponseEntity.ok(weeklyFreePeriods);
        } catch (Exception e) {
            throw new AppException("Failed to get weekly free periods: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get teacher workload statistics
    @GetMapping("/teacher/{teacherId}/stats")
    public ResponseEntity<Map<String, Object>> getTeacherWorkloadStats(@PathVariable String teacherId) {
        try {
            Map<String, Object> stats = timetableService.getTeacherWorkloadStats(teacherId);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            throw new AppException("Failed to get teacher stats: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get available teachers for a time slot
    @GetMapping("/available-teachers")
    public ResponseEntity<List<String>> getAvailableTeachers(
            @RequestParam String dayOfWeek, @RequestParam Integer periodNumber) {
        try {
            List<String> availableTeachers = timetableService.getAvailableTeachers(dayOfWeek, periodNumber);
            return ResponseEntity.ok(availableTeachers);
        } catch (Exception e) {
            throw new AppException("Failed to get available teachers: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get room schedule
    @GetMapping("/room/{classRoom}/schedule/{dayOfWeek}")
    public ResponseEntity<List<TimetableDto>> getRoomSchedule(
            @PathVariable String classRoom, @PathVariable String dayOfWeek) {
        try {
            List<TimetableDto> roomSchedule = timetableService.getRoomSchedule(classRoom, dayOfWeek);
            return ResponseEntity.ok(roomSchedule);
        } catch (Exception e) {
            throw new AppException("Failed to get room schedule: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get detailed available time slots with availability status
    @GetMapping("/teacher/{teacherId}/detailed-slots/{dayOfWeek}")
    public ResponseEntity<List<TimeSlotDto>> getDetailedAvailableTimeSlots(
            @PathVariable String teacherId, @PathVariable String dayOfWeek) {
        try {
            List<TimeSlotDto> detailedSlots = timetableService.getDetailedAvailableTimeSlots(teacherId, dayOfWeek);
            return ResponseEntity.ok(detailedSlots);
        } catch (Exception e) {
            throw new AppException("Failed to get detailed time slots: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Keep all your existing endpoints...
    @GetMapping
    public ResponseEntity<List<TimetableDto>> getAllTimetables() {
        try {
            List<TimetableDto> timetables = timetableService.getAllTimetables();
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimetableDto> getTimetableById(@PathVariable Long id) {
        try {
            TimetableDto timetable = timetableService.getTimetableById(id);
            return ResponseEntity.ok(timetable);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimetableDto> updateTimetable(@PathVariable Long id, @RequestBody TimetableDto timetableDto) {
        try {
            TimetableDto updatedTimetable = timetableService.updateTimetable(id, timetableDto);
            return ResponseEntity.ok(updatedTimetable);
        } catch (Exception e) {
            throw new AppException("Failed to update timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TimetableDto> deleteTimetable(@PathVariable Long id) {
        try {
            TimetableDto deletedTimetable = timetableService.deleteTimetable(id);
            return ResponseEntity.ok(deletedTimetable);
        } catch (Exception e) {
            throw new AppException("Failed to delete timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TEACHER SPECIFIC ENDPOINTS (Keep your existing ones)
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TimetableDto>> getTimetablesByTeacherId(@PathVariable String teacherId) {
        try {
            List<TimetableDto> timetables = timetableService.getTimetablesByTeacherId(teacherId);
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve teacher timetables: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacher/{teacherId}/weekly")
    public ResponseEntity<TimetableWeeklyDto> getWeeklyTimetableByTeacherId(@PathVariable String teacherId) {
        try {
            TimetableWeeklyDto weeklyTimetable = timetableService.getWeeklyTimetableByTeacherId(teacherId);
            return ResponseEntity.ok(weeklyTimetable);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve weekly timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacher/{teacherId}/academic-year/{academicYear}")
    public ResponseEntity<List<TimetableDto>> getTimetablesByTeacherIdAndAcademicYear(
            @PathVariable String teacherId, @PathVariable String academicYear) {
        try {
            List<TimetableDto> timetables = timetableService.getTimetablesByTeacherIdAndAcademicYear(teacherId, academicYear);
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables by academic year: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacher/{teacherId}/academic-year/{academicYear}/term/{term}")
    public ResponseEntity<List<TimetableDto>> getTimetablesByTeacherIdAndTerm(
            @PathVariable String teacherId, @PathVariable String academicYear, @PathVariable String term) {
        try {
            List<TimetableDto> timetables = timetableService.getTimetablesByTeacherIdAndTerm(teacherId, academicYear, term);
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables by term: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacher/{teacherId}/day/{dayOfWeek}")
    public ResponseEntity<List<TimetableDto>> getDailyTimetableByTeacherId(
            @PathVariable String teacherId, @PathVariable String dayOfWeek) {
        try {
            List<TimetableDto> timetables = timetableService.getDailyTimetableByTeacherId(teacherId, dayOfWeek);
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve daily timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // SEARCH AND FILTER ENDPOINTS (Keep your existing ones)
    @GetMapping("/search/teacher")
    public ResponseEntity<List<TimetableDto>> searchTimetablesByTeacherName(@RequestParam String teacherName) {
        try {
            List<TimetableDto> timetables = timetableService.searchTimetablesByTeacherName(teacherName);
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            throw new AppException("Failed to search timetables by teacher name: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subject/{subject}")
    public ResponseEntity<List<TimetableDto>> getTimetablesBySubject(@PathVariable String subject) {
        try {
            List<TimetableDto> timetables = timetableService.getTimetablesBySubject(subject);
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables by subject: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<TimetableDto>> getTimetablesByGrade(@PathVariable String grade) {
        try {
            List<TimetableDto> timetables = timetableService.getTimetablesByGrade(grade);
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables by grade: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/academic-year/{academicYear}/term/{term}")
    public ResponseEntity<List<TimetableDto>> getTimetablesByAcademicYearAndTerm(
            @PathVariable String academicYear, @PathVariable String term) {
        try {
            List<TimetableDto> timetables = timetableService.getTimetablesByAcademicYearAndTerm(academicYear, term);
            return ResponseEntity.ok(timetables);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables by academic year and term: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UTILITY ENDPOINTS (Keep your existing ones)
    @GetMapping("/conflict-check")
    public ResponseEntity<Boolean> checkTimeConflict(
            @RequestParam String teacherId,
            @RequestParam String dayOfWeek,
            @RequestParam String startTime,
            @RequestParam String endTime,
            @RequestParam(required = false) Long excludeId) {
        try {
            boolean hasConflict = timetableService.checkTimeConflict(teacherId, dayOfWeek, startTime, endTime, excludeId);
            return ResponseEntity.ok(hasConflict);
        } catch (Exception e) {
            throw new AppException("Failed to check time conflict: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacher/{teacherId}/available-slots/{dayOfWeek}")
    public ResponseEntity<List<String>> getAvailableTimeSlots(
            @PathVariable String teacherId, @PathVariable String dayOfWeek) {
        try {
            List<String> availableSlots = timetableService.getAvailableTimeSlots(teacherId, dayOfWeek);
            return ResponseEntity.ok(availableSlots);
        } catch (Exception e) {
            throw new AppException("Failed to get available time slots: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/free-teachers")
    public ResponseEntity<List<Map<String, Object>>> getFreeTeachers(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Integer periodNumber) {
        try {
            List<Map<String, Object>> freeTeachers = timetableService.getFreeTeachersForPeriod(date, periodNumber);
            return ResponseEntity.ok(freeTeachers);
        } catch (Exception e) {
            throw new AppException("Failed to get free teachers: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teachers-status")
    public ResponseEntity<List<Map<String, Object>>> getAllTeachersStatus(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Integer periodNumber) {
        try {
            List<Map<String, Object>> teachersStatus = timetableService.getAllTeachersStatusForPeriod(date, periodNumber);
            return ResponseEntity.ok(teachersStatus);
        } catch (Exception e) {
            throw new AppException("Failed to get teachers status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
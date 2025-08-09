package com.bit.backend.services.impl;

import com.bit.backend.dtos.TimeSlotDto;
import com.bit.backend.dtos.TimetableDto;
import com.bit.backend.dtos.TimetableWeeklyDto;
import com.bit.backend.entities.TimetableEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.TimetableMapper;
import com.bit.backend.repositories.TimetableRepository;
import com.bit.backend.services.TimetableServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import com.bit.backend.repositories.AcademicStaffRepository;
import com.bit.backend.entities.AcademicStaffEntity;
import com.bit.backend.repositories.TeacherAbsenceRepository;
import com.bit.backend.entities.TeacherAbsenceEntity;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TimetableService implements TimetableServiceI {

    private final TimetableRepository timetableRepository;
    private final TimetableMapper timetableMapper;
    private final AcademicStaffRepository academicStaffRepository;
    private final TeacherAbsenceRepository teacherAbsenceRepository;

    // Define standard time slots mapping
    private static final Map<Integer, TimeSlotDto> STANDARD_TIME_SLOTS = new HashMap<>();
    private static final List<String> WEEK_DAYS = Arrays.asList(
            "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"
    );

    static {
        STANDARD_TIME_SLOTS.put(1, new TimeSlotDto(1, LocalTime.of(8, 0), LocalTime.of(8, 40), "Period 1", true));
        STANDARD_TIME_SLOTS.put(2, new TimeSlotDto(2, LocalTime.of(8, 40), LocalTime.of(9, 20), "Period 2", true));
        STANDARD_TIME_SLOTS.put(3, new TimeSlotDto(3, LocalTime.of(9, 40), LocalTime.of(10, 20), "Period 3", true)); // After break
        STANDARD_TIME_SLOTS.put(4, new TimeSlotDto(4, LocalTime.of(10, 20), LocalTime.of(11, 0), "Period 4", true));
        STANDARD_TIME_SLOTS.put(5, new TimeSlotDto(5, LocalTime.of(11, 0), LocalTime.of(11, 40), "Period 5", true));
        STANDARD_TIME_SLOTS.put(6, new TimeSlotDto(6, LocalTime.of(11, 40), LocalTime.of(12, 20), "Period 6", true));
        STANDARD_TIME_SLOTS.put(7, new TimeSlotDto(7, LocalTime.of(13, 0), LocalTime.of(13, 40), "Period 7", true)); // After lunch
        STANDARD_TIME_SLOTS.put(8, new TimeSlotDto(8, LocalTime.of(13, 40), LocalTime.of(14, 20), "Period 8", true));
    }

    public TimetableService(TimetableRepository timetableRepository,
                            TimetableMapper timetableMapper,
                            AcademicStaffRepository academicStaffRepository,
                            TeacherAbsenceRepository teacherAbsenceRepository) {
        this.timetableRepository = timetableRepository;
        this.timetableMapper = timetableMapper;
        this.academicStaffRepository = academicStaffRepository;
        this.teacherAbsenceRepository = teacherAbsenceRepository;
    }

    /**
     * Enhanced addTimetable with automatic time slot mapping
     */
    @Override
    public TimetableDto addTimetable(TimetableDto timetableDto) {
        try {
            // Automatically map period number to time slot
            if (timetableDto.getPeriodNumber() != null) {
                TimeSlotDto timeSlot = getTimeSlotByPeriod(timetableDto.getPeriodNumber());
                if (timeSlot != null) {
                    timetableDto.setStartTime(timeSlot.getStartTime());
                    timetableDto.setEndTime(timeSlot.getEndTime());
                }
            }

            // Validate time slot
            if (timetableDto.getStartTime() == null || timetableDto.getEndTime() == null) {
                throw new AppException("Invalid period number or time slot", HttpStatus.BAD_REQUEST);
            }

            // Check for time conflicts
            if (checkTimeConflict(timetableDto.getTeacherId(), timetableDto.getDayOfWeek(),
                    timetableDto.getStartTime().toString(), timetableDto.getEndTime().toString(), null)) {
                throw new AppException("Time conflict detected for the specified time slot", HttpStatus.BAD_REQUEST);
            }

            timetableDto.setStatus(1); // Set as active
            TimetableEntity timetableEntity = timetableMapper.toTimetableEntity(timetableDto);
            TimetableEntity savedEntity = timetableRepository.save(timetableEntity);
            return timetableMapper.toTimetableDto(savedEntity);
        } catch (Exception e) {
            throw new AppException("Failed to create timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get time slot information by period number
     */
    public TimeSlotDto getTimeSlotByPeriod(Integer periodNumber) {
        return STANDARD_TIME_SLOTS.get(periodNumber);
    }

    /**
     * Get all standard time slots
     */
    @Override
    public List<TimeSlotDto> getAllStandardTimeSlots() {
        return new ArrayList<>(STANDARD_TIME_SLOTS.values());
    }

    /**
     * Get free periods for a specific teacher on a specific day
     */
    public List<TimeSlotDto> getFreePeriodsByDay(String teacherId, String dayOfWeek) {
        try {
            List<TimetableEntity> occupiedSlots = timetableRepository
                    .findByTeacherIdAndDayOfWeekAndStatusOrderByPeriodNumberAsc(teacherId, dayOfWeek.toUpperCase(), 1);

            Set<Integer> occupiedPeriods = occupiedSlots.stream()
                    .map(TimetableEntity::getPeriodNumber)
                    .collect(Collectors.toSet());

            return STANDARD_TIME_SLOTS.entrySet().stream()
                    .filter(entry -> !occupiedPeriods.contains(entry.getKey()))
                    .map(entry -> {
                        TimeSlotDto slot = entry.getValue();
                        return new TimeSlotDto(slot.getPeriodNumber(), slot.getStartTime(),
                                slot.getEndTime(), slot.getTimeSlotLabel(), true);
                    })
                    .sorted(Comparator.comparing(TimeSlotDto::getPeriodNumber))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new AppException("Failed to get free periods for day: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get free periods for entire week for a specific teacher
     */
    public Map<String, List<TimeSlotDto>> getFreePeriodsWeek(String teacherId) {
        try {
            Map<String, List<TimeSlotDto>> weeklyFreePeriods = new HashMap<>();

            for (String day : WEEK_DAYS) {
                weeklyFreePeriods.put(day, getFreePeriodsByDay(teacherId, day));
            }

            return weeklyFreePeriods;
        } catch (Exception e) {
            throw new AppException("Failed to get weekly free periods: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get teacher's workload statistics
     */
    public Map<String, Object> getTeacherWorkloadStats(String teacherId) {
        try {
            List<TimetableEntity> allClasses = timetableRepository
                    .findByTeacherIdAndStatusOrderByDayOfWeekAscPeriodNumberAsc(teacherId, 1);

            Map<String, Long> classesByDay = allClasses.stream()
                    .collect(Collectors.groupingBy(TimetableEntity::getDayOfWeek, Collectors.counting()));

            Map<String, Long> classesBySubject = allClasses.stream()
                    .collect(Collectors.groupingBy(TimetableEntity::getSubject, Collectors.counting()));

            int totalClasses = allClasses.size();
            int totalPossibleSlots = WEEK_DAYS.size() * STANDARD_TIME_SLOTS.size();
            double utilizationRate = (double) totalClasses / totalPossibleSlots * 100;

            Map<String, Object> stats = new HashMap<>();
            stats.put("totalClasses", totalClasses);
            stats.put("totalFreeSlots", totalPossibleSlots - totalClasses);
            stats.put("totalPossibleSlots", totalPossibleSlots);
            stats.put("utilizationRate", Math.round(utilizationRate * 100.0) / 100.0);
            stats.put("classesByDay", classesByDay);
            stats.put("classesBySubject", classesBySubject);
            stats.put("teacherId", teacherId);

            return stats;
        } catch (Exception e) {
            throw new AppException("Failed to get teacher workload stats: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find available teachers for a specific time slot
     */
    public List<String> getAvailableTeachers(String dayOfWeek, Integer periodNumber) {
        try {
            TimeSlotDto timeSlot = getTimeSlotByPeriod(periodNumber);
            if (timeSlot == null) {
                throw new AppException("Invalid period number", HttpStatus.BAD_REQUEST);
            }

            // Get all teachers who are occupied during this time slot
            List<TimetableEntity> occupiedTeachers = timetableRepository
                    .findConflictingTimetables("", dayOfWeek.toUpperCase(),
                            timeSlot.getStartTime(), timeSlot.getEndTime(), null);

            Set<String> occupiedTeacherIds = occupiedTeachers.stream()
                    .map(TimetableEntity::getTeacherId)
                    .collect(Collectors.toSet());

            // This would need to be enhanced to get all teachers from your teacher/employee repository
            // For now, returning the concept
            return new ArrayList<>(); // Implement based on your teacher repository
        } catch (Exception e) {
            throw new AppException("Failed to get available teachers: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get room schedule for a specific day
     */
    public List<TimetableDto> getRoomSchedule(String classRoom, String dayOfWeek) {
        try {
            // This would need a custom repository method
            // For now, showing the concept
            List<TimetableEntity> roomSchedule = timetableRepository
                    .findByClassRoomAndDayOfWeekAndStatusOrderByPeriodNumberAsc(classRoom, dayOfWeek.toUpperCase(), 1);

            return timetableMapper.toTimetableDtoList(roomSchedule);
        } catch (Exception e) {
            throw new AppException("Failed to get room schedule: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Enhanced detailed available time slots with more information
     */
    @Override
    public List<TimeSlotDto> getDetailedAvailableTimeSlots(String teacherId, String dayOfWeek) {
        try {
            List<TimetableEntity> occupiedSlots = timetableRepository
                    .findByTeacherIdAndDayOfWeekAndStatusOrderByPeriodNumberAsc(teacherId, dayOfWeek.toUpperCase(), 1);

            Set<Integer> occupiedPeriods = occupiedSlots.stream()
                    .map(TimetableEntity::getPeriodNumber)
                    .collect(Collectors.toSet());

            return STANDARD_TIME_SLOTS.entrySet().stream()
                    .map(entry -> {
                        TimeSlotDto slot = entry.getValue();
                        boolean isAvailable = !occupiedPeriods.contains(entry.getKey());
                        return new TimeSlotDto(slot.getPeriodNumber(), slot.getStartTime(),
                                slot.getEndTime(), slot.getTimeSlotLabel(), isAvailable);
                    })
                    .sorted(Comparator.comparing(TimeSlotDto::getPeriodNumber))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new AppException("Failed to get detailed available time slots: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Keep all existing methods from your original TimetableService implementation
    @Override
    public List<TimetableDto> getAllTimetables() {
        try {
            List<TimetableEntity> timetableEntities = timetableRepository.findByStatusOrderByTeacherNameAscDayOfWeekAscPeriodNumberAsc(1);
            return timetableMapper.toTimetableDtoList(timetableEntities);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TimetableDto getTimetableById(Long id) {
        try {
            Optional<TimetableEntity> optionalTimetable = timetableRepository.findById(id);
            if (!optionalTimetable.isPresent()) {
                throw new AppException("Timetable not found", HttpStatus.NOT_FOUND);
            }
            return timetableMapper.toTimetableDto(optionalTimetable.get());
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TimetableDto updateTimetable(Long id, TimetableDto timetableDto) {
        try {
            Optional<TimetableEntity> optionalTimetable = timetableRepository.findById(id);
            if (!optionalTimetable.isPresent()) {
                throw new AppException("Timetable not found", HttpStatus.NOT_FOUND);
            }

            // Automatically map period number to time slot if period is provided
            if (timetableDto.getPeriodNumber() != null) {
                TimeSlotDto timeSlot = getTimeSlotByPeriod(timetableDto.getPeriodNumber());
                if (timeSlot != null) {
                    timetableDto.setStartTime(timeSlot.getStartTime());
                    timetableDto.setEndTime(timeSlot.getEndTime());
                }
            }

            // Check for time conflicts (excluding current record)
            if (checkTimeConflict(timetableDto.getTeacherId(), timetableDto.getDayOfWeek(),
                    timetableDto.getStartTime().toString(), timetableDto.getEndTime().toString(), id)) {
                throw new AppException("Time conflict detected for the specified time slot", HttpStatus.BAD_REQUEST);
            }

            TimetableEntity updatedEntity = timetableMapper.toTimetableEntity(timetableDto);
            updatedEntity.setId(id);

            TimetableEntity savedEntity = timetableRepository.save(updatedEntity);
            return timetableMapper.toTimetableDto(savedEntity);
        } catch (Exception e) {
            throw new AppException("Failed to update timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TimetableDto deleteTimetable(Long id) {
        try {
            Optional<TimetableEntity> optionalTimetable = timetableRepository.findById(id);
            if (!optionalTimetable.isPresent()) {
                throw new AppException("Timetable not found", HttpStatus.NOT_FOUND);
            }

            TimetableEntity timetableEntity = optionalTimetable.get();
            timetableEntity.setStatus(0); // Soft delete
            timetableRepository.save(timetableEntity);

            return timetableMapper.toTimetableDto(timetableEntity);
        } catch (Exception e) {
            throw new AppException("Failed to delete timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TimetableDto> getTimetablesByTeacherId(String teacherId) {
        try {
            List<TimetableEntity> timetableEntities = timetableRepository.findByTeacherIdAndStatusOrderByDayOfWeekAscPeriodNumberAsc(teacherId, 1);
            return timetableMapper.toTimetableDtoList(timetableEntities);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve teacher timetables: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public TimetableWeeklyDto getWeeklyTimetableByTeacherId(String teacherId) {
        try {
            List<TimetableEntity> timetableEntities = timetableRepository.findByTeacherIdAndStatusOrderByDayOfWeekAscPeriodNumberAsc(teacherId, 1);

            if (timetableEntities.isEmpty()) {
                throw new AppException("No timetable found for teacher", HttpStatus.NOT_FOUND);
            }

            Map<String, List<TimetableDto>> weeklySchedule = timetableEntities.stream()
                    .map(timetableMapper::toTimetableDto)
                    .collect(Collectors.groupingBy(TimetableDto::getDayOfWeek));

            TimetableWeeklyDto weeklyDto = new TimetableWeeklyDto();
            weeklyDto.setTeacherId(teacherId);
            weeklyDto.setTeacherName(timetableEntities.get(0).getTeacherName());
            weeklyDto.setAcademicYear(timetableEntities.get(0).getAcademicYear());
            weeklyDto.setTerm(timetableEntities.get(0).getTerm());
            weeklyDto.setWeeklySchedule(weeklySchedule);

            return weeklyDto;
        } catch (Exception e) {
            throw new AppException("Failed to retrieve weekly timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TimetableDto> getTimetablesByTeacherIdAndAcademicYear(String teacherId, String academicYear) {
        try {
            List<TimetableEntity> timetableEntities = timetableRepository.findByTeacherIdAndAcademicYearAndStatusOrderByDayOfWeekAscPeriodNumberAsc(teacherId, academicYear, 1);
            return timetableMapper.toTimetableDtoList(timetableEntities);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables by academic year: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TimetableDto> getTimetablesByTeacherIdAndTerm(String teacherId, String academicYear, String term) {
        try {
            List<TimetableEntity> timetableEntities = timetableRepository.findByTeacherIdAndAcademicYearAndTermAndStatusOrderByDayOfWeekAscPeriodNumberAsc(teacherId, academicYear, term, 1);
            return timetableMapper.toTimetableDtoList(timetableEntities);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables by term: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TimetableDto> getDailyTimetableByTeacherId(String teacherId, String dayOfWeek) {
        try {
            List<TimetableEntity> timetableEntities = timetableRepository.findByTeacherIdAndDayOfWeekAndStatusOrderByPeriodNumberAsc(teacherId, dayOfWeek, 1);
            return timetableMapper.toTimetableDtoList(timetableEntities);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve daily timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TimetableDto> searchTimetablesByTeacherName(String teacherName) {
        try {
            List<TimetableEntity> timetableEntities = timetableRepository.findByTeacherNameContainingIgnoreCaseAndStatusOrderByDayOfWeekAscPeriodNumberAsc(teacherName, 1);
            return timetableMapper.toTimetableDtoList(timetableEntities);
        } catch (Exception e) {
            throw new AppException("Failed to search timetables by teacher name: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TimetableDto> getTimetablesBySubject(String subject) {
        try {
            List<TimetableEntity> timetableEntities = timetableRepository.findBySubjectContainingIgnoreCaseAndStatusOrderByTeacherNameAscDayOfWeekAscPeriodNumberAsc(subject, 1);
            return timetableMapper.toTimetableDtoList(timetableEntities);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables by subject: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TimetableDto> getTimetablesByGrade(String grade) {
        try {
            List<TimetableEntity> timetableEntities = timetableRepository.findByGradeAndStatusOrderByTeacherNameAscDayOfWeekAscPeriodNumberAsc(grade, 1);
            return timetableMapper.toTimetableDtoList(timetableEntities);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables by grade: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TimetableDto> getTimetablesByAcademicYearAndTerm(String academicYear, String term) {
        try {
            List<TimetableEntity> timetableEntities = timetableRepository.findByAcademicYearAndTermAndStatusOrderByTeacherNameAscDayOfWeekAscPeriodNumberAsc(academicYear, term, 1);
            return timetableMapper.toTimetableDtoList(timetableEntities);
        } catch (Exception e) {
            throw new AppException("Failed to retrieve timetables by academic year and term: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean checkTimeConflict(String teacherId, String dayOfWeek, String startTime, String endTime, Long excludeId) {
        try {
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);

            List<TimetableEntity> conflicts = timetableRepository.findConflictingTimetables(teacherId, dayOfWeek, start, end, excludeId);
            return !conflicts.isEmpty();
        } catch (Exception e) {
            throw new AppException("Failed to check time conflict: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<String> getAvailableTimeSlots(String teacherId, String dayOfWeek) {
        try {
            List<TimeSlotDto> freeSlots = getFreePeriodsByDay(teacherId, dayOfWeek);
            return freeSlots.stream()
                    .map(slot -> slot.getStartTime() + "-" + slot.getEndTime())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new AppException("Failed to get available time slots: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Map<String, Object>> getFreeTeachersForPeriod(LocalDate date, Integer periodNumber) {
        try {
            // Get day of week from date
            String dayOfWeek = date.getDayOfWeek()
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();

            // Get all academic staff (teachers) from repository
            List<AcademicStaffEntity> allTeachers = academicStaffRepository.findAll();

            // Get all timetables for this day and period to find busy teachers
            List<TimetableEntity> busyTimetables = timetableRepository
                    .findByDayOfWeekAndPeriodNumberAndStatus(dayOfWeek, periodNumber, 1);

            // Create set of busy teacher IDs (using teacherNumber as ID)
            Set<String> busyTeacherIds = busyTimetables.stream()
                    .map(TimetableEntity::getTeacherId)
                    .collect(Collectors.toSet());

            // Get absent teachers for this date
            Set<String> absentTeacherIds = new HashSet<>();
            List<TeacherAbsenceEntity> absences = teacherAbsenceRepository
                    .findByAbsenceDateAndStatus(date, "ACTIVE");
            absentTeacherIds = absences.stream()
                    .map(TeacherAbsenceEntity::getTeacherId)
                    .collect(Collectors.toSet());

            // Filter to get only free and present teachers
            Set<String> finalAbsentTeacherIds = absentTeacherIds;
            List<Map<String, Object>> freeTeachers = allTeachers.stream()
                    .filter(teacher -> {
                        String teacherId = teacher.getTeacherNumber().toString();
                        // Teacher must be:
                        // 1. Not busy in this period
                        // 2. Not absent on this date
                        return !busyTeacherIds.contains(teacherId) && !finalAbsentTeacherIds.contains(teacherId);
                    })
                    .map(teacher -> {
                        Map<String, Object> teacherInfo = new HashMap<>();
                        teacherInfo.put("teacherId", teacher.getTeacherNumber().toString());
                        teacherInfo.put("teacherName", teacher.getNameWithInitials());
                        teacherInfo.put("teacherFullName", teacher.getNameinFull());
                        teacherInfo.put("isFree", true);
                        teacherInfo.put("isPresent", true); // Teacher is present (not absent)
                        teacherInfo.put("date", date.toString());
                        teacherInfo.put("dayOfWeek", dayOfWeek);
                        teacherInfo.put("periodNumber", periodNumber);
                        teacherInfo.put("subject", teacher.getSubjectTeaching1());
                        teacherInfo.put("email", teacher.getEmailAddress());
                        teacherInfo.put("contactNumber", teacher.getTelephone());

                        // Get time slot info
                        TimeSlotDto timeSlot = getTimeSlotByPeriod(periodNumber);
                        if (timeSlot != null) {
                            teacherInfo.put("startTime", timeSlot.getStartTime().toString());
                            teacherInfo.put("endTime", timeSlot.getEndTime().toString());
                            teacherInfo.put("periodLabel", timeSlot.getTimeSlotLabel());
                        }

                        // Get teacher's total free periods for the day
                        String teacherIdStr = teacher.getTeacherNumber().toString();
                        List<TimeSlotDto> dayFreePeriods = getFreePeriodsByDay(teacherIdStr, dayOfWeek);
                        teacherInfo.put("totalFreePeriodsToday", dayFreePeriods.size());

                        return teacherInfo;
                    })
                    .sorted((a, b) -> {
                        // Sort by teacher name
                        String nameA = (String) a.get("teacherName");
                        String nameB = (String) b.get("teacherName");
                        return nameA.compareTo(nameB);
                    })
                    .collect(Collectors.toList());

            return freeTeachers;
        } catch (Exception e) {
            throw new AppException("Failed to get free teachers for period: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Map<String, Object>> getAllTeachersStatusForPeriod(LocalDate date, Integer periodNumber) {
        try {
            // Get day of week from date
            String dayOfWeek = date.getDayOfWeek()
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();

            // Get all academic staff (teachers) from repository
            List<AcademicStaffEntity> allTeachers = academicStaffRepository.findAll();

            // Get all timetables for this day and period to find busy teachers
            List<TimetableEntity> busyTimetables = timetableRepository
                    .findByDayOfWeekAndPeriodNumberAndStatus(dayOfWeek, periodNumber, 1);

            // Create map of busy teacher IDs to their assigned class info
            Map<String, TimetableEntity> busyTeacherMap = busyTimetables.stream()
                    .collect(Collectors.toMap(
                            TimetableEntity::getTeacherId,
                            t -> t,
                            (existing, replacement) -> existing
                    ));

            // Get absent teachers for this date
            Map<String, TeacherAbsenceEntity> absentTeacherMap = new HashMap<>();
            List<TeacherAbsenceEntity> absences = teacherAbsenceRepository
                    .findByAbsenceDateAndStatus(date, "ACTIVE");
            for (TeacherAbsenceEntity absence : absences) {
                absentTeacherMap.put(absence.getTeacherId(), absence);
            }

            // Create status for all teachers
            List<Map<String, Object>> allTeachersStatus = allTeachers.stream()
                    .map(teacher -> {
                        Map<String, Object> teacherInfo = new HashMap<>();
                        String teacherId = teacher.getTeacherNumber().toString();

                        teacherInfo.put("teacherId", teacherId);
                        teacherInfo.put("teacherName", teacher.getNameWithInitials());
                        teacherInfo.put("teacherFullName", teacher.getNameinFull());
                        teacherInfo.put("date", date.toString());
                        teacherInfo.put("dayOfWeek", dayOfWeek);
                        teacherInfo.put("periodNumber", periodNumber);
                        teacherInfo.put("subject", teacher.getSubjectTeaching1());

                        // Determine teacher status
                        String status;
                        String statusReason = null;
                        boolean isAvailable = false;

                        if (absentTeacherMap.containsKey(teacherId)) {
                            status = "ABSENT";
                            TeacherAbsenceEntity absence = absentTeacherMap.get(teacherId);
                            statusReason = absence.getReason() != null ? absence.getReason() : "Marked absent";
                        } else if (busyTeacherMap.containsKey(teacherId)) {
                            status = "BUSY";
                            TimetableEntity busySlot = busyTeacherMap.get(teacherId);
                            statusReason = String.format("Teaching %s - Grade %s at %s",
                                    busySlot.getSubject(), busySlot.getGrade(), busySlot.getClassRoom());
                        } else {
                            status = "FREE";
                            isAvailable = true;
                        }

                        teacherInfo.put("status", status);
                        teacherInfo.put("isAvailable", isAvailable);
                        teacherInfo.put("statusReason", statusReason);

                        // Get time slot info
                        TimeSlotDto timeSlot = getTimeSlotByPeriod(periodNumber);
                        if (timeSlot != null) {
                            teacherInfo.put("startTime", timeSlot.getStartTime().toString());
                            teacherInfo.put("endTime", timeSlot.getEndTime().toString());
                            teacherInfo.put("periodLabel", timeSlot.getTimeSlotLabel());
                        }

                        return teacherInfo;
                    })
                    .sorted((a, b) -> {
                        // Sort by status (FREE first, then BUSY, then ABSENT) and then by name
                        String statusA = (String) a.get("status");
                        String statusB = (String) b.get("status");
                        int statusCompare = getStatusPriority(statusA) - getStatusPriority(statusB);
                        if (statusCompare != 0) {
                            return statusCompare;
                        }
                        String nameA = (String) a.get("teacherName");
                        String nameB = (String) b.get("teacherName");
                        return nameA.compareTo(nameB);
                    })
                    .collect(Collectors.toList());

            return allTeachersStatus;
        } catch (Exception e) {
            throw new AppException("Failed to get teacher status for period: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int getStatusPriority(String status) {
        switch (status) {
            case "FREE": return 1;
            case "BUSY": return 2;
            case "ABSENT": return 3;
            default: return 4;
        }
    }
}

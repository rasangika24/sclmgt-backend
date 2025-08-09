package com.bit.backend.services.impl;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ReliefPeriodMapper;
import com.bit.backend.mappers.TeacherAbsenceMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.ReliefPeriodServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReliefPeriodService implements ReliefPeriodServiceI {

    private final ReliefPeriodRepository reliefPeriodRepository;
    private final TeacherAbsenceRepository teacherAbsenceRepository;
    private final TimetableRepository timetableRepository;
    private final EmployeeRepository employeeRepository;
    private final ReliefPeriodMapper reliefPeriodMapper;
    private final TeacherAbsenceMapper teacherAbsenceMapper;

    public ReliefPeriodService(ReliefPeriodRepository reliefPeriodRepository,
                               TeacherAbsenceRepository teacherAbsenceRepository,
                               TimetableRepository timetableRepository,
                               EmployeeRepository employeeRepository,
                               ReliefPeriodMapper reliefPeriodMapper,
                               TeacherAbsenceMapper teacherAbsenceMapper) {
        this.reliefPeriodRepository = reliefPeriodRepository;
        this.teacherAbsenceRepository = teacherAbsenceRepository;
        this.timetableRepository = timetableRepository;
        this.employeeRepository = employeeRepository;
        this.reliefPeriodMapper = reliefPeriodMapper;
        this.teacherAbsenceMapper = teacherAbsenceMapper;
    }

    @Override
    public TeacherAbsenceDto markTeacherAbsent(TeacherAbsenceDto teacherAbsenceDto) {
        try {
            // Check if already marked absent
            Optional<TeacherAbsenceEntity> existing = teacherAbsenceRepository
                    .findByTeacherIdAndAbsenceDate(teacherAbsenceDto.getTeacherId(),
                            teacherAbsenceDto.getAbsenceDate());

            if (existing.isPresent()) {
                throw new AppException("Teacher already marked absent for this date", HttpStatus.CONFLICT);
            }

            TeacherAbsenceEntity entity = teacherAbsenceMapper.toTeacherAbsenceEntity(teacherAbsenceDto);
            TeacherAbsenceEntity saved = teacherAbsenceRepository.save(entity);

            // Create relief period entries for all the teacher's periods on that day
            createReliefPeriodsForAbsentTeacher(saved);

            return teacherAbsenceMapper.toTeacherAbsenceDto(saved);
        } catch (Exception e) {
            throw new AppException("Failed to mark teacher absent: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void createReliefPeriodsForAbsentTeacher(TeacherAbsenceEntity absence) {
        String dayOfWeek = absence.getAbsenceDate().getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();

        // Get teacher's timetable for that day
        List<TimetableEntity> timetables = timetableRepository
                .findByTeacherIdAndDayOfWeekAndStatusOrderByPeriodNumberAsc(
                        absence.getTeacherId(), dayOfWeek, 1);

        // Create relief period entries
        for (TimetableEntity timetable : timetables) {
            ReliefPeriodEntity reliefPeriod = new ReliefPeriodEntity();
            reliefPeriod.setAbsentTeacherId(absence.getTeacherId());
            reliefPeriod.setAbsentTeacherName(absence.getTeacherName());
            reliefPeriod.setDate(absence.getAbsenceDate());
            reliefPeriod.setDayOfWeek(dayOfWeek);
            reliefPeriod.setPeriodNumber(timetable.getPeriodNumber());
            reliefPeriod.setSubject(timetable.getSubject());
            reliefPeriod.setGrade(timetable.getGrade());
            reliefPeriod.setClassRoom(timetable.getClassRoom());
            reliefPeriod.setStatus("PENDING");

            reliefPeriodRepository.save(reliefPeriod);
        }
    }

    @Override
    public List<TeacherAbsenceDto> getAbsentTeachers(LocalDate date) {
        try {
            List<TeacherAbsenceEntity> absences = teacherAbsenceRepository
                    .findByAbsenceDateAndStatus(date, "ACTIVE");
            return teacherAbsenceMapper.toTeacherAbsenceDtoList(absences);
        } catch (Exception e) {
            throw new AppException("Failed to get absent teachers: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TeacherAbsenceDto> getAllActiveAbsences() {
        try {
            List<TeacherAbsenceEntity> absences = teacherAbsenceRepository
                    .findByStatusOrderByAbsenceDateDesc("ACTIVE");
            return teacherAbsenceMapper.toTeacherAbsenceDtoList(absences);
        } catch (Exception e) {
            throw new AppException("Failed to get active absences: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<TimetableDto> getAbsentTeacherTimetable(String teacherId, LocalDate date) {
        try {
            String dayOfWeek = date.getDayOfWeek()
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();

            List<TimetableEntity> timetables = timetableRepository
                    .findByTeacherIdAndDayOfWeekAndStatusOrderByPeriodNumberAsc(teacherId, dayOfWeek, 1);

            // Convert to DTOs
            List<TimetableDto> timetableDtos = new ArrayList<>();
            for (TimetableEntity entity : timetables) {
                TimetableDto dto = new TimetableDto();
                dto.setId(entity.getId());
                dto.setTeacherId(entity.getTeacherId());
                dto.setTeacherName(entity.getTeacherName());
                dto.setDayOfWeek(entity.getDayOfWeek());
                dto.setPeriodNumber(entity.getPeriodNumber());
                dto.setStartTime(entity.getStartTime());
                dto.setEndTime(entity.getEndTime());
                dto.setSubject(entity.getSubject());
                dto.setGrade(entity.getGrade());
                dto.setClassRoom(entity.getClassRoom());
                timetableDtos.add(dto);
            }

            return timetableDtos;
        } catch (Exception e) {
            throw new AppException("Failed to get absent teacher timetable: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AvailableTeacherDto> getAvailableTeachers(LocalDate date, Integer periodNumber) {
        try {
            String dayOfWeek = date.getDayOfWeek()
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();

            // Get all teachers
            List<Map<String, Object>> allTeachers = employeeRepository.getTeachersList();

            // Get teachers who are busy in this period
            List<TimetableEntity> busyTeachers = timetableRepository
                    .findByPeriodNumberAndStatusOrderByTeacherNameAscDayOfWeekAsc(periodNumber, 1);

            Set<String> busyTeacherIds = busyTeachers.stream()
                    .filter(t -> t.getDayOfWeek().equals(dayOfWeek))
                    .map(TimetableEntity::getTeacherId)
                    .collect(Collectors.toSet());

            // Get teachers already assigned for relief on this date and period
            List<ReliefPeriodEntity> assignedRelief = reliefPeriodRepository
                    .findConflicts(date, periodNumber, "");

            Set<String> reliefTeacherIds = assignedRelief.stream()
                    .map(ReliefPeriodEntity::getReliefTeacherId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            // Get absent teachers
            List<TeacherAbsenceEntity> absences = teacherAbsenceRepository
                    .findByAbsenceDateAndStatus(date, "ACTIVE");

            Set<String> absentTeacherIds = absences.stream()
                    .map(TeacherAbsenceEntity::getTeacherId)
                    .collect(Collectors.toSet());

            // Build available teachers list
            List<AvailableTeacherDto> availableTeachers = new ArrayList<>();
            for (Map<String, Object> teacher : allTeachers) {
                String teacherId = (String) teacher.get("id");
                String teacherName = (String) teacher.get("name");

                boolean isAvailable = !busyTeacherIds.contains(teacherId)
                        && !reliefTeacherIds.contains(teacherId)
                        && !absentTeacherIds.contains(teacherId);

                availableTeachers.add(new AvailableTeacherDto(teacherId, teacherName, isAvailable, null));
            }

            return availableTeachers;
        } catch (Exception e) {
            throw new AppException("Failed to get available teachers: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ReliefPeriodDto assignReliefTeacher(ReliefPeriodDto reliefPeriodDto) {
        try {
            // Check if relief teacher is available
            List<ReliefPeriodEntity> conflicts = reliefPeriodRepository.findConflicts(
                    reliefPeriodDto.getDate(),
                    reliefPeriodDto.getPeriodNumber(),
                    reliefPeriodDto.getReliefTeacherId());

            if (!conflicts.isEmpty()) {
                throw new AppException("Teacher is not available for this period", HttpStatus.CONFLICT);
            }

            ReliefPeriodEntity entity;
            if (reliefPeriodDto.getId() != null) {
                // Update existing
                Optional<ReliefPeriodEntity> existing = reliefPeriodRepository.findById(reliefPeriodDto.getId());
                if (!existing.isPresent()) {
                    throw new AppException("Relief period not found", HttpStatus.NOT_FOUND);
                }
                entity = existing.get();
                entity.setReliefTeacherId(reliefPeriodDto.getReliefTeacherId());
                entity.setReliefTeacherName(reliefPeriodDto.getReliefTeacherName());
                entity.setStatus("ASSIGNED");
                entity.setAssignedBy(reliefPeriodDto.getAssignedBy());
            } else {
                // Create new
                entity = reliefPeriodMapper.toReliefPeriodEntity(reliefPeriodDto);
                entity.setStatus("ASSIGNED");
            }

            ReliefPeriodEntity saved = reliefPeriodRepository.save(entity);
            return reliefPeriodMapper.toReliefPeriodDto(saved);
        } catch (Exception e) {
            throw new AppException("Failed to assign relief teacher: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ReliefPeriodDto> getReliefPeriods(LocalDate date) {
        try {
            List<ReliefPeriodEntity> reliefPeriods = reliefPeriodRepository.findByDateOrderByPeriodNumberAsc(date);
            return reliefPeriodMapper.toReliefPeriodDtoList(reliefPeriods);
        } catch (Exception e) {
            throw new AppException("Failed to get relief periods: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ReliefPeriodDto> getReliefPeriodsByTeacher(String teacherId, LocalDate date) {
        try {
            List<ReliefPeriodEntity> reliefPeriods = reliefPeriodRepository
                    .findByReliefTeacherIdAndDateOrderByPeriodNumberAsc(teacherId, date);
            return reliefPeriodMapper.toReliefPeriodDtoList(reliefPeriods);
        } catch (Exception e) {
            throw new AppException("Failed to get relief periods by teacher: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ReliefPeriodDto updateReliefPeriod(Long id, ReliefPeriodDto reliefPeriodDto) {
        try {
            Optional<ReliefPeriodEntity> existing = reliefPeriodRepository.findById(id);
            if (!existing.isPresent()) {
                throw new AppException("Relief period not found", HttpStatus.NOT_FOUND);
            }

            ReliefPeriodEntity entity = existing.get();
            entity.setReliefTeacherId(reliefPeriodDto.getReliefTeacherId());
            entity.setReliefTeacherName(reliefPeriodDto.getReliefTeacherName());
            entity.setRemarks(reliefPeriodDto.getRemarks());
            entity.setStatus(reliefPeriodDto.getStatus());

            ReliefPeriodEntity saved = reliefPeriodRepository.save(entity);
            return reliefPeriodMapper.toReliefPeriodDto(saved);
        } catch (Exception e) {
            throw new AppException("Failed to update relief period: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ReliefPeriodDto cancelReliefPeriod(Long id) {
        try {
            Optional<ReliefPeriodEntity> existing = reliefPeriodRepository.findById(id);
            if (!existing.isPresent()) {
                throw new AppException("Relief period not found", HttpStatus.NOT_FOUND);
            }

            ReliefPeriodEntity entity = existing.get();
            entity.setStatus("CANCELLED");
            entity.setReliefTeacherId(null);
            entity.setReliefTeacherName(null);

            ReliefPeriodEntity saved = reliefPeriodRepository.save(entity);
            return reliefPeriodMapper.toReliefPeriodDto(saved);
        } catch (Exception e) {
            throw new AppException("Failed to cancel relief period: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Map<String, Object> getReliefPeriodReport(LocalDate startDate, LocalDate endDate) {
        try {
            List<ReliefPeriodEntity> reliefPeriods = reliefPeriodRepository.findByDateRange(startDate, endDate);

            Map<String, Object> report = new HashMap<>();
            report.put("totalReliefPeriods", reliefPeriods.size());
            report.put("assignedPeriods", reliefPeriods.stream()
                    .filter(r -> "ASSIGNED".equals(r.getStatus())).count());
            report.put("pendingPeriods", reliefPeriods.stream()
                    .filter(r -> "PENDING".equals(r.getStatus())).count());
            report.put("completedPeriods", reliefPeriods.stream()
                    .filter(r -> "COMPLETED".equals(r.getStatus())).count());

            // Group by teacher
            Map<String, Long> reliefByTeacher = reliefPeriods.stream()
                    .filter(r -> r.getReliefTeacherId() != null)
                    .collect(Collectors.groupingBy(ReliefPeriodEntity::getReliefTeacherName, Collectors.counting()));
            report.put("reliefByTeacher", reliefByTeacher);

            return report;
        } catch (Exception e) {
            throw new AppException("Failed to generate report: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
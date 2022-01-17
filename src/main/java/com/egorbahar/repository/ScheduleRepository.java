package com.egorbahar.repository;

import com.egorbahar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findSchedulesByRecruiter_Id(Long recruiterId);
    List<Schedule> findSchedulesByRecruiter_IdAndStartTimeBetween(Long recruiterId, @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
            LocalDateTime today, @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDateTime tomorrow);
    List<Schedule> findSchedulesByEngineer_IdAndStartTimeBetween(Long engineerId, @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
            LocalDateTime today, @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDateTime tomorrow);
    List<Schedule> findSchedulesByEngineer_Id(Long engineerId);
}

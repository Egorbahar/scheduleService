package com.egorbahar.repository;

import com.egorbahar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findSchedulesByRecruiter_Id(Long recruiterId);
    List<Schedule> findSchedulesByEngineer_Id(Long engineerId);
}

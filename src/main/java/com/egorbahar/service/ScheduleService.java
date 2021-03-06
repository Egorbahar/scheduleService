package com.egorbahar.service;

import com.egorbahar.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule save(Schedule schedule);

    void deleteById(Long id);

    Schedule update(Schedule schedule);

    List<Schedule> findAll();

    Schedule findById(Long id);

    List<Schedule> findByRecruiterId(Long id);

    List<Schedule> findByEngineerId(Long id);
}

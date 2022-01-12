package com.egorbahar.service;

import com.egorbahar.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    void save(Schedule schedule);

    void deleteById(Long id);

    Schedule update(Schedule schedule);

    List<Schedule> findAll();

    Schedule findById(Long id);
}

package com.egorbahar.service.impl;

import com.egorbahar.component.LocalMessageSource;
import com.egorbahar.entity.Schedule;
import com.egorbahar.repository.ScheduleRepository;
import com.egorbahar.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final LocalMessageSource messageSource;
    @Override
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        scheduleRepository.deleteById(id);
    }

    @Override
    public Schedule update(Schedule schedule) {
        findById(schedule.getId());
        return scheduleRepository.saveAndFlush(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(()->new RuntimeException(messageSource.getMessage("error.schedule.notExist", new Object[]{})));
    }
}

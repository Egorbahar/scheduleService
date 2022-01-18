package com.egorbahar.controller;

import com.egorbahar.dto.request.ScheduleRequestDto;
import com.egorbahar.dto.response.ScheduleResponseDto;
import com.egorbahar.entity.Schedule;
import com.egorbahar.mapper.ScheduleMapper;
import com.egorbahar.service.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/schedules")
@Slf4j
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleMapper.toScheduleResponseDto(scheduleService.findById(id));
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        scheduleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd.yyyy");
        LocalDate localDate = LocalDate.parse(scheduleRequestDto.getDate(), formatter );
        LocalTime localTime = LocalTime.parse(scheduleRequestDto.getTime());
        LocalDateTime localDateTime = LocalDateTime.of(localDate,localTime);
        Schedule schedule = scheduleMapper.toSchedule(scheduleRequestDto);
        schedule.setStartTime(localDateTime);
        final ScheduleResponseDto scheduleResponseDto = scheduleMapper.toScheduleResponseDto(scheduleService.save(schedule));
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleMapper.toSchedule(scheduleRequestDto);
        schedule.setId(id);
        scheduleService.update(schedule);
        ScheduleResponseDto scheduleResponseDto = scheduleMapper.toScheduleResponseDto(schedule);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedulesByRole( @RequestParam(required = false) String role,  @RequestParam(required = false) Long userId) {

        List<ScheduleResponseDto> scheduleResponseDtoList = new ArrayList<>();

        if (role != null)
        {
            switch (role) {
                case "recruiter":
                    scheduleResponseDtoList = scheduleMapper.toScheduleResponseDtoList(scheduleService.findByRecruiterId(userId));
                    break;
                case "engineer":
                    scheduleResponseDtoList = scheduleMapper.toScheduleResponseDtoList(scheduleService.findByEngineerId(userId));
                    break;
            }
        }
        else {
            scheduleResponseDtoList = scheduleMapper.toScheduleResponseDtoList(scheduleService.findAll());
        }
        scheduleResponseDtoList
                .forEach(s -> s.setEndTime(LocalDateTime.parse(s.getStartTime()).plusMinutes(s.getDuration()).toString()));

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }
}

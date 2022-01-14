package com.egorbahar.controller;

import com.egorbahar.dto.request.VacancyRequestDto;
import com.egorbahar.dto.response.VacancyResponseDto;
import com.egorbahar.entity.Department;
import com.egorbahar.entity.Vacancy;
import com.egorbahar.enums.Position;
import com.egorbahar.mapper.VacancyMapper;
import com.egorbahar.repository.DepartmentRepository;
import com.egorbahar.service.DepartmentService;
import com.egorbahar.service.VacancyService;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.joda.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/vacancies")
@AllArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final DepartmentService departmentService;
    private final VacancyMapper vacancyMapper;

    @GetMapping
    public ResponseEntity<List<VacancyResponseDto>> getAll() {
        List<VacancyResponseDto> vacancyResponseDtoList = vacancyMapper.toVacancyResponseDtoList(vacancyService.findAll());
        return new ResponseEntity<>(vacancyResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacancyResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        VacancyResponseDto vacancyResponseDto = vacancyMapper.toVacancyResponseDto(vacancyService.findById(id));
        return new ResponseEntity<>(vacancyResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        vacancyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public void save(@Valid @RequestBody VacancyRequestDto vacancyRequestDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Vacancy vacancy = vacancyMapper.toVacancy(vacancyRequestDto);
        vacancy.setDate(localDateTime);
        List<Position> positionList = Arrays.asList(Position.values());
        Optional<Position> position = positionList.stream()
                .filter(pos -> pos.getPosition().equals(vacancyRequestDto.getPosition()))
                .findFirst();
        vacancy.setPosition(position.get());
//        Department department = departmentService.findByName(vacancyRequestDto.getDepartmentName());
//        vacancy.setDepartment(department);
        vacancyService.save(vacancy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacancyResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody VacancyRequestDto vacancyRequestDto) {
        Vacancy vacancy = vacancyMapper.toVacancy(vacancyRequestDto);
        vacancy.setId(id);
        vacancyService.update(vacancy);
        VacancyResponseDto vacancyResponseDto = vacancyMapper.toVacancyResponseDto(vacancy);
        return new ResponseEntity<>(vacancyResponseDto, HttpStatus.OK);
    }

}

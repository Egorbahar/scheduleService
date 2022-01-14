package com.egorbahar.controller;

import com.egorbahar.dto.request.DepartmentRequestDto;
import com.egorbahar.dto.request.VacancyRequestDto;
import com.egorbahar.dto.response.DepartmentResponseDto;
import com.egorbahar.entity.Department;
import com.egorbahar.entity.Vacancy;
import com.egorbahar.mapper.DepartmentMapper;
import com.egorbahar.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAll() {
        List<DepartmentResponseDto> departmentResponseDtoList = departmentMapper.toDepartmentResponseDtoList(departmentService.findAll());
        return new ResponseEntity<>(departmentResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        DepartmentResponseDto departmentResponseDto = departmentMapper.toDepartmentResponseDto(departmentService.findById(id));
        return new ResponseEntity<>(departmentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public void save(@Valid @RequestBody DepartmentRequestDto departmentRequestDto)
    {
        Department department = departmentMapper.toDepartment(departmentRequestDto);
        departmentService.save(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
        Department department = departmentMapper.toDepartment(departmentRequestDto);
        department.setId(id);
        departmentService.update(department);
        DepartmentResponseDto departmentResponseDto = departmentMapper.toDepartmentResponseDto(department);
        return new ResponseEntity<>(departmentResponseDto, HttpStatus.OK);
    }
}
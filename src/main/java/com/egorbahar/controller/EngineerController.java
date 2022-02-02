package com.egorbahar.controller;

import com.egorbahar.dto.request.EngineerRequestDto;
import com.egorbahar.dto.response.EngineerResponseDto;
import com.egorbahar.entity.Department;
import com.egorbahar.entity.Engineer;
import com.egorbahar.enums.Position;
import com.egorbahar.mapper.EngineerMapper;
import com.egorbahar.service.DepartmentService;
import com.egorbahar.service.EngineerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/engineers")
@AllArgsConstructor
public class EngineerController {
    private final EngineerService engineerService;
    private final DepartmentService departmentService;
    private final EngineerMapper engineerMapper;

    @GetMapping
    public ResponseEntity<List<EngineerResponseDto>> getAll() {
        List<EngineerResponseDto> engineerResponseDtoList = engineerMapper.toEngineerResponseDtoList(engineerService.findAll());
        return new ResponseEntity<>(engineerResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EngineerResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        EngineerResponseDto engineerResponseDto = engineerMapper.toEngineerResponseDto(engineerService.findById(id));
        return new ResponseEntity<>(engineerResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        engineerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EngineerResponseDto> save(@Valid @RequestBody EngineerRequestDto engineerRequestDto) {
        Optional<Position> position = Arrays.stream(Position.values())
                .filter(pos -> pos.getPosition().equals(engineerRequestDto.getPosition()))
                .findFirst();
        Engineer engineer = engineerMapper.toEngineer(engineerRequestDto);
        engineer.setPosition(position.get());
        engineer.setDepartment(departmentService.findById(engineerRequestDto.getDepartmentId()));
        final EngineerResponseDto engineerResponseDto = engineerMapper.toEngineerResponseDto(engineerService.save(engineer));
        return new ResponseEntity<>(engineerResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EngineerResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody EngineerRequestDto engineerRequestDto) {
        Engineer engineer = engineerMapper.toEngineer(engineerRequestDto);
        engineer.setId(id);
        engineerService.update(engineer);
        EngineerResponseDto engineerResponseDto = engineerMapper.toEngineerResponseDto(engineer);
        return new ResponseEntity<>(engineerResponseDto, HttpStatus.OK);
    }
}

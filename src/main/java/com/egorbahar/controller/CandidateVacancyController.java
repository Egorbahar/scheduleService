package com.egorbahar.controller;

import com.egorbahar.dto.request.CandidateVacancyRequestDto;
import com.egorbahar.dto.response.CandidateVacancyResponseDto;
import com.egorbahar.entity.CandidateVacancy;
import com.egorbahar.entity.Company;
import com.egorbahar.mapper.CandidateVacancyMapper;
import com.egorbahar.service.CandidateVacancyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/candidatevacancies")
public class CandidateVacancyController {
    private final CandidateVacancyService candidateVacancyService;
    private final CandidateVacancyMapper candidateVacancyMapper;
    @GetMapping
    public ResponseEntity<List<CandidateVacancyResponseDto>> getAll() {
        List<CandidateVacancyResponseDto> candidateVacancyResponseDtoList = candidateVacancyMapper.toCandidateVacancyResponseDtoList(candidateVacancyService.findAll());
        return new ResponseEntity<>(candidateVacancyResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateVacancyResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        CandidateVacancyResponseDto candidateVacancyResponseDto = candidateVacancyMapper.toCandidateVacancyResponseDto(candidateVacancyService.findById(id));
        return new ResponseEntity<>(candidateVacancyResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        candidateVacancyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public void save(@Valid @RequestBody CandidateVacancyRequestDto candidateVacancyRequestDto) {
        CandidateVacancy candidateVacancy = candidateVacancyMapper.toCandidateVacancy(candidateVacancyRequestDto);
        candidateVacancyService.save(candidateVacancy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateVacancyResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody CandidateVacancyRequestDto candidateVacancyRequestDto) {
        CandidateVacancy candidateVacancy = candidateVacancyMapper.toCandidateVacancy(candidateVacancyRequestDto);
        candidateVacancy.setId(id);
        candidateVacancyService.update(candidateVacancy);
        CandidateVacancyResponseDto candidateVacancyResponseDto = candidateVacancyMapper.toCandidateVacancyResponseDto(candidateVacancy);
        return new ResponseEntity<>(candidateVacancyResponseDto, HttpStatus.OK);
    }
}
package com.egorbahar.controller;

import com.egorbahar.dto.request.CandidateRequestDto;
import com.egorbahar.dto.response.CandidateResponseDto;
import com.egorbahar.entity.Candidate;
import com.egorbahar.mapper.CandidateMapper;
import com.egorbahar.service.CandidateService;
import com.egorbahar.service.CandidateVacancyService;
import com.egorbahar.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;
    private final CompanyService companyService;
    private final CandidateVacancyService candidateVacancyService;

    @GetMapping
    public ResponseEntity<List<CandidateResponseDto>> getAll() {
        List<CandidateResponseDto> candidateResponseDtoList = candidateMapper.toCandidateResponseDtoList(candidateService.findAll());
        for (CandidateResponseDto candidateResponseDto : candidateResponseDtoList) {
            List<String> vacancyNameList = candidateVacancyService.findAll().stream()
                    .filter(candidateVacancy -> candidateVacancy.getCandidate().getId().equals(candidateResponseDto.getId()))
                    .map(candidateVacancy -> candidateVacancy.getVacancy().getName())
                    .collect(Collectors.toList());
            candidateResponseDto.setVacancyNameList(vacancyNameList);
        }
        return new ResponseEntity<>(candidateResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        CandidateResponseDto candidateResponseDto = candidateMapper.toCandidateResponseDto(candidateService.findById(id));
        List<String> vacancyNameList = candidateVacancyService.findAll().stream()
                .filter(candidateVacancy -> candidateVacancy.getCandidate().getId().equals(id))
                .map(candidateVacancy -> candidateVacancy.getVacancy().getName())
                .collect(Collectors.toList());
        candidateResponseDto.setVacancyNameList(vacancyNameList);
        return new ResponseEntity<>(candidateResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        candidateService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CandidateResponseDto> save(@Valid @RequestBody CandidateRequestDto candidateRequestDto) {
        Candidate candidate = candidateMapper.toCandidate(candidateRequestDto);
        candidate.setCompany(companyService.findById(candidateRequestDto.getCompanyId()));
        CandidateResponseDto candidateResponseDto = candidateMapper.toCandidateResponseDto(candidateService.save(candidate));
            return new ResponseEntity<>(candidateResponseDto, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody CandidateRequestDto candidateRequestDto) {
        Candidate candidate = candidateMapper.toCandidate(candidateRequestDto);
        candidate.setId(id);
        candidateService.update(candidate);
        CandidateResponseDto candidateResponseDto = candidateMapper.toCandidateResponseDto(candidate);
        return new ResponseEntity<>(candidateResponseDto, HttpStatus.OK);
    }
}

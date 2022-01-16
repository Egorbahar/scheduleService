package com.egorbahar.controller;

import com.egorbahar.dto.request.CandidateRequestDto;
import com.egorbahar.dto.response.CandidateResponseDto;
import com.egorbahar.entity.Candidate;
import com.egorbahar.entity.Company;
import com.egorbahar.mapper.CandidateMapper;
import com.egorbahar.service.CandidateService;
import com.egorbahar.service.CandidateVacancyService;
import com.egorbahar.service.CompanyService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;
    private final CompanyService companyService;
    private final CandidateVacancyService candidateVacancyService;

    @GetMapping
    public ResponseEntity<List<CandidateResponseDto>> getAll() {
        List<CandidateResponseDto> candidateResponseDtoList = candidateMapper.toCandidateResponseDtoList(candidateService.findAll());
        for (CandidateResponseDto candidate : candidateResponseDtoList) {
            List<Long> vacanciesId = candidateVacancyService.findAll().stream()
                    .filter(candidateVacancy -> candidateVacancy.getCandidate().getId().equals(candidate.getId()))
                    .map(candidateVacancy -> candidateVacancy.getVacancy().getId())
                    .collect(Collectors.toList());
            candidate.setVacancyId(vacanciesId);
        }
        return new ResponseEntity<>(candidateResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        CandidateResponseDto candidateResponseDto = candidateMapper.toCandidateResponseDto(candidateService.findById(id));
        List<Long> vacanciesId = candidateVacancyService.findAll().stream()
                .filter(candidateVacancy -> candidateVacancy.getCandidate().getId().equals(id))
                .map(candidateVacancy -> candidateVacancy.getVacancy().getId())
                .collect(Collectors.toList());
        candidateResponseDto.setVacancyId(vacanciesId);
        return new ResponseEntity<>(candidateResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        candidateService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public void save(@Valid @RequestBody CandidateRequestDto candidateRequestDto) {
        Company company = new Company();
        company.setName(candidateRequestDto.getCompanyName());
        if (company.getName() != null) {
            companyService.save(company);
            Candidate candidate = candidateMapper.toCandidate(candidateRequestDto);
            candidate.setCompany(company);
            candidateService.save(candidate);
        } else {
            Candidate candidate = candidateMapper.toCandidate(candidateRequestDto);
            candidateService.save(candidate);
        }

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

package com.egorbahar.controller;

import com.egorbahar.dto.request.CandidateRequestDto;
import com.egorbahar.dto.response.CandidateResponseDto;
import com.egorbahar.entity.Candidate;
import com.egorbahar.mapper.CandidateMapper;
import com.egorbahar.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/candidates")
@AllArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;

    @GetMapping
    public ResponseEntity<List<CandidateResponseDto>> getAll() {
        List<CandidateResponseDto> candidateResponseDtoList = candidateMapper.toCandidateResponseDtoList(candidateService.findAll());
        return new ResponseEntity<>(candidateResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        CandidateResponseDto candidateResponseDto = candidateMapper.toCandidateResponseDto(candidateService.findById(id));
        return new ResponseEntity<>(candidateResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        candidateService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
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

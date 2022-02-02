package com.egorbahar.controller;

import com.egorbahar.dto.request.RecruiterRequestDto;
import com.egorbahar.dto.response.RecruiterResponseDto;
import com.egorbahar.entity.Recruiter;
import com.egorbahar.entity.Recruiter;
import com.egorbahar.mapper.RecruiterMapper;
import com.egorbahar.service.RecruiterService;
import com.egorbahar.service.RecruiterService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/recruiters")
@RequiredArgsConstructor
public class RecruiterController {
    private final RecruiterService recruiterService;
    private final RecruiterMapper recruiterMapper;

    @GetMapping
    public ResponseEntity<List<RecruiterResponseDto>> getAll() {
        List<RecruiterResponseDto> recruiterResponseDtoList = recruiterMapper.toRecruiterResponseDtoList(recruiterService.findAll());
        return new ResponseEntity<>(recruiterResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruiterResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        RecruiterResponseDto recruiterResponseDto = recruiterMapper.toRecruiterResponseDto(recruiterService.findById(id));
        return new ResponseEntity<>(recruiterResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecruiterResponseDto> save(@Valid @RequestBody RecruiterRequestDto recruiterRequestDto) {
        final RecruiterResponseDto recruiterResponseDto = recruiterMapper.toRecruiterResponseDto(recruiterService.save(recruiterMapper.toRecruiter(recruiterRequestDto)));
        return new ResponseEntity<>(recruiterResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        recruiterService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecruiterResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody RecruiterRequestDto recruiterRequestDto) {
        Recruiter recruiter = recruiterMapper.toRecruiter(recruiterRequestDto);
        recruiter.setId(id);
        recruiterService.update(recruiter);
        RecruiterResponseDto recruiterResponseDto = recruiterMapper.toRecruiterResponseDto(recruiter);
        return new ResponseEntity<>(recruiterResponseDto, HttpStatus.OK);
    }
}

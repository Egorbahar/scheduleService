package com.egorbahar.controller;

import com.egorbahar.dto.request.CompanyRequestDto;
import com.egorbahar.dto.request.CompanyRequestDto;
import com.egorbahar.dto.request.ScheduleRequestDto;
import com.egorbahar.dto.response.CompanyResponseDto;
import com.egorbahar.dto.response.CompanyResponseDto;
import com.egorbahar.entity.Company;
import com.egorbahar.entity.Company;
import com.egorbahar.entity.Role;
import com.egorbahar.entity.User;
import com.egorbahar.mapper.CompanyMapper;
import com.egorbahar.repository.RoleRepository;
import com.egorbahar.repository.UserRepository;
import com.egorbahar.service.CompanyService;
import com.egorbahar.service.CompanyService;
import com.egorbahar.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAll() {
        List<CompanyResponseDto> companyResponseDtoList = companyMapper.toCompanyResponseDtoList(companyService.findAll());
        return new ResponseEntity<>(companyResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        CompanyResponseDto companyResponseDto = companyMapper.toCompanyResponseDto(companyService.findById(id));
        return new ResponseEntity<>(companyResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        companyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CompanyResponseDto> save(@Valid @RequestBody CompanyRequestDto companyRequestDto) {
        CompanyResponseDto companyResponseDto = companyMapper.toCompanyResponseDto(companyService.save(companyMapper.toCompany(companyRequestDto)));
        return new ResponseEntity<>(companyResponseDto,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody CompanyRequestDto companyRequestDto) {
        Company company = companyMapper.toCompany(companyRequestDto);
        company.setId(id);
        companyService.update(company);
        CompanyResponseDto companyResponseDto = companyMapper.toCompanyResponseDto(company);
        return new ResponseEntity<>(companyResponseDto, HttpStatus.OK);
    }
}

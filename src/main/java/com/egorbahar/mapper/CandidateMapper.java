package com.egorbahar.mapper;

import com.egorbahar.dto.request.CandidateRequestDto;
import com.egorbahar.dto.response.CandidateResponseDto;
import com.egorbahar.entity.Candidate;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    @Mapping(target = "id" , source = "id")
    @Mapping(target = "company", source = "company.name")
    @Mapping(target = "vacancyId", ignore = true)
    CandidateResponseDto toCandidateResponseDto(Candidate candidate);

    @Mapping(target = "id" ,ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "candidateVacancies", ignore = true)
    Candidate toCandidate(CandidateRequestDto candidateRequestDto);

    @IterableMapping(elementTargetType = CandidateResponseDto.class)
    @Mapping(target = "company", source = "company.name")
    @Mapping(target = "candidateVacancyId", source = "candidateVacancies")
    List<CandidateResponseDto> toCandidateResponseDtoList(Collection<Candidate> candidates);
}

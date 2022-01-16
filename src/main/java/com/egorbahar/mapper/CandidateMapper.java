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
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "vacancyId", ignore = true)
    CandidateResponseDto toCandidateResponseDto(Candidate candidate);

    @Mapping(target = "id" ,ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "candidateVacancies", ignore = true)
    Candidate toCandidate(CandidateRequestDto candidateRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Candidate candidate, CandidateRequestDto candidateRequestDto);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "candidateVacancyId", source = "candidateVacancies")
    @IterableMapping(elementTargetType = CandidateResponseDto.class)
    List<CandidateResponseDto> toCandidateResponseDtoList(Collection<Candidate> candidates);
}

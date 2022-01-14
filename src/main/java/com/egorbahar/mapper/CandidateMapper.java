package com.egorbahar.mapper;

import com.egorbahar.dto.request.CandidateRequestDto;
import com.egorbahar.dto.response.CandidateResponseDto;
import com.egorbahar.entity.Candidate;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    CandidateResponseDto toCandidateResponseDto(Candidate candidate);

    @Mapping(target = "id", ignore = true)
    Candidate toCandidate(CandidateRequestDto candidateRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Candidate candidate, CandidateRequestDto candidateRequestDto);

    @IterableMapping(elementTargetType = CandidateResponseDto.class)
    List<CandidateResponseDto> toCandidateResponseDtoList(Collection<Candidate> candidates);
}

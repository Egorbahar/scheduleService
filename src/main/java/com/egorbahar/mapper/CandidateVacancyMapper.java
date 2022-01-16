package com.egorbahar.mapper;

import com.egorbahar.dto.request.CandidateVacancyRequestDto;
import com.egorbahar.dto.response.CandidateVacancyResponseDto;
import com.egorbahar.entity.CandidateVacancy;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateVacancyMapper {
    @Mapping(target = "candidateId",source = "candidate.id" )
    @Mapping(target = "vacancyId", source = "vacancy.id")
    CandidateVacancyResponseDto toCandidateVacancyResponseDto(CandidateVacancy candidateVacancy);

    @Mapping(target = "candidate.id" , source = "candidateVacancyRequestDto.candidateId")
    @Mapping(target = "vacancy.id" , source = "candidateVacancyRequestDto.vacancyId")
    CandidateVacancy toCandidateVacancy(CandidateVacancyRequestDto candidateVacancyRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget CandidateVacancy candidateVacancy, CandidateVacancyRequestDto candidateVacancyRequestDto);

    @IterableMapping(elementTargetType = CandidateVacancyResponseDto.class)
    List<CandidateVacancyResponseDto> toCandidateVacancyResponseDtoList(Collection<CandidateVacancy> candidateVacancies);
}

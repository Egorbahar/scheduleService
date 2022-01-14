package com.egorbahar.mapper;

import com.egorbahar.dto.request.VacancyRequestDto;
import com.egorbahar.dto.response.VacancyResponseDto;
import com.egorbahar.entity.Vacancy;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    VacancyResponseDto toVacancyResponseDto(Vacancy vacancy);

    @Mapping(target = "id", ignore = true)
    Vacancy toVacancy(VacancyRequestDto vacancyRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Vacancy vacancy, VacancyRequestDto vacancyDto);

    @IterableMapping(elementTargetType = VacancyResponseDto.class)
    List<VacancyResponseDto> toVacancyResponseDtoList(Collection<Vacancy> vacancies);
}

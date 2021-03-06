package com.egorbahar.mapper;

import com.egorbahar.dto.request.VacancyRequestDto;
import com.egorbahar.dto.response.VacancyResponseDto;
import com.egorbahar.entity.Vacancy;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VacancyMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "vacancy.department.name" , target = "department")
    VacancyResponseDto toVacancyResponseDto(Vacancy vacancy);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "vacancyRequestDto.name", target = "name")
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "position", ignore = true)
    @Mapping(target = "department" , ignore = true)
    Vacancy toVacancy(VacancyRequestDto vacancyRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department" , ignore = true)
    void updateEntity(@MappingTarget Vacancy vacancy, VacancyRequestDto vacancyDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "vacancies.department.name" , target = "department")
    @IterableMapping(elementTargetType = VacancyResponseDto.class)
    List<VacancyResponseDto> toVacancyResponseDtoList(Collection<Vacancy> vacancies);
}

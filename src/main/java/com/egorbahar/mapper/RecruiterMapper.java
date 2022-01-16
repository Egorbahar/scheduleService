package com.egorbahar.mapper;

import com.egorbahar.dto.request.RecruiterRequestDto;
import com.egorbahar.dto.response.RecruiterResponseDto;
import com.egorbahar.dto.response.VacancyResponseDto;
import com.egorbahar.entity.Recruiter;
import com.egorbahar.entity.Vacancy;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RecruiterMapper {
    RecruiterResponseDto toRecruiterResponseDto(Recruiter recruiter);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    Recruiter toRecruiter(RecruiterRequestDto recruiterRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Recruiter recruiter, RecruiterRequestDto recruiterRequestDto);

    @Mapping(source = "id", target = "id")
    @IterableMapping(elementTargetType = RecruiterResponseDto.class)
    List<RecruiterResponseDto> toRecruiterResponseDtoList(Collection<Recruiter> recruiters);
}

package com.egorbahar.mapper;

import com.egorbahar.dto.request.ScheduleRequestDto;
import com.egorbahar.dto.response.ScheduleResponseDto;
import com.egorbahar.entity.Schedule;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "duration", source = "category.duration")
    @Mapping(target = "candidateName", source = "candidateVacancy.candidate.name")
    @Mapping(target = "candidateSurname", source = "candidateVacancy.candidate.surname")
    ScheduleResponseDto toScheduleResponseDto(Schedule schedule);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startTime" , ignore = true)
    Schedule toSchedule(ScheduleRequestDto scheduleRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Schedule schedule, ScheduleRequestDto scheduleRequestDto);

    @IterableMapping(elementTargetType = ScheduleResponseDto.class)
    List<ScheduleResponseDto> toScheduleResponseDtoList(Collection<Schedule> schedules);
}

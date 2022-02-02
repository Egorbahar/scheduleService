package com.egorbahar.mapper;

import com.egorbahar.dto.request.ScheduleRequestDto;
import com.egorbahar.dto.response.ScheduleResponseDto;
import com.egorbahar.entity.Schedule;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    @Mapping(target = "category", source = "schedule.category.name")
    @Mapping(target = "duration", source = "schedule.category.duration")
    @Mapping(target = "candidateName", source = "schedule.candidateVacancy.candidate.name")
    @Mapping(target = "candidateSurname", source = "schedule.candidateVacancy.candidate.surname")
    @Mapping(target = "vacancy", source = "schedule.candidateVacancy.vacancy.name")
    ScheduleResponseDto toScheduleResponseDto(Schedule schedule);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startTime" , ignore = true)
    @Mapping(target = "category.id" , source = "categoryId")
    @Mapping(target = "candidateVacancy.id" , source = "candidateVacancyId")
    @Mapping(target = "engineer.id" , source = "engineerId")
    @Mapping(target = "recruiter.id" , source = "recruiterId")
    Schedule toSchedule(ScheduleRequestDto scheduleRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Schedule schedule, ScheduleRequestDto scheduleRequestDto);

    @IterableMapping(elementTargetType = ScheduleResponseDto.class)
    List<ScheduleResponseDto> toScheduleResponseDtoList(Collection<Schedule> schedules);
}

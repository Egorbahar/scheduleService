package com.egorbahar.mapper;

import com.egorbahar.dto.request.ScheduleRequestDto;
import com.egorbahar.dto.response.ScheduleResponseDto;
import com.egorbahar.entity.Schedule;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    ScheduleResponseDto toScheduleResponseDto(Schedule schedule);

    @Mapping(target = "id", ignore = true)
    Schedule toSchedule(ScheduleRequestDto scheduleRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Schedule schedule, ScheduleRequestDto scheduleRequestDto);

    @IterableMapping(elementTargetType = ScheduleResponseDto.class)
    List<ScheduleResponseDto> toScheduleResponseDtoList(Collection<Schedule> schedules);
}

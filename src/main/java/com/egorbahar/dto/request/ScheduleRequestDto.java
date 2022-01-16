package com.egorbahar.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    String date;
    String time;
    Long categoryId;
    Long recruiterId;
    Long engineerId;
    Long candidateVacancyId;
}

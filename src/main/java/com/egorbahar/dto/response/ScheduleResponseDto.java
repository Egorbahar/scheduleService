package com.egorbahar.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleResponseDto {
    private Long id;
    private String date;
    private String startTime;
    private String endTime;
    private String candidateName;
    private String candidateSurname;
    private String category;

}

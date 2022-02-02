package com.egorbahar.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateVacancyResponseDto {
    private Long id;
    private String candidate;
    private String vacancy;
}

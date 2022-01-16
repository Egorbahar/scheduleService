package com.egorbahar.dto.request;

import lombok.Data;

@Data
public class CandidateVacancyRequestDto {
    private Long id;
    private Long candidateId;
    private Long vacancyId;
}

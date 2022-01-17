package com.egorbahar.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CandidateResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String company;
    private List<Long> vacancyId;
}

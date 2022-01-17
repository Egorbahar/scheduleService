package com.egorbahar.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CandidateRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    private String email;
    @NotNull
    private Long companyId;
}

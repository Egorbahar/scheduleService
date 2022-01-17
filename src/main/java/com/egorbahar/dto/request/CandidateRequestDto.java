package com.egorbahar.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CandidateRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    private String company;
    private List<Long> vacancy_id;
}

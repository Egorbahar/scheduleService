package com.egorbahar.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineerResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String position;
    private String department;
}

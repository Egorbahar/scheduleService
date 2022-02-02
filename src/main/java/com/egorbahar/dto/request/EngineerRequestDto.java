package com.egorbahar.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngineerRequestDto {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String position;
    private Long departmentId;
}

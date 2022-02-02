package com.egorbahar.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruiterResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String phoneNumber;
}

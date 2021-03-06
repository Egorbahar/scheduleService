package com.egorbahar.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
public class RegistrationRequestDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}

package com.egorbahar.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class RegistrationRequestDto {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
}

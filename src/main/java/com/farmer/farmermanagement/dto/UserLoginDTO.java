package com.farmer.farmermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotBlank
    private String emailOrPhone;

    @NotBlank
    private String password;
}

package com.farmer.farmermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "Email or Phone number is required")
    private String emailOrPhone;  

    @NotBlank(message = "Password is required")
    private String password;
}


package com.farmer.farmermanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegistrationDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;  // You can use LocalDate if needed

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Country/Region is required")
    private String country;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Pin Code is required")
    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid Pin Code")
    private String pinCode;

    @NotBlank(message = "Time zone is required")
    private String timeZone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;
}

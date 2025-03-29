package com.farmer.farmermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @Email(message = "Invalid email format.")
    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits.")
    private String phoneNumber;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, message = "Password must be at least 6 characters long.")
    private String password;

    @NotBlank(message = "Date of birth is required.")
    private String dateOfBirth; // Format: YYYY-MM-DD

    @NotBlank(message = "Gender is required.")
    private String gender;

    @NotBlank(message = "Country is required.")
    private String country;

    @NotBlank(message = "State is required.")
    private String state;

    @NotBlank(message = "PIN code is required.")
    @Pattern(regexp = "^[0-9]{6}$", message = "PIN code must be 6 digits.")
    private String pinCode;

    @NotBlank(message = "Time zone is required.")
    private String timeZone;

    public String getEmail() { return email; }
public String getPhoneNumber() { return phoneNumber; }
}

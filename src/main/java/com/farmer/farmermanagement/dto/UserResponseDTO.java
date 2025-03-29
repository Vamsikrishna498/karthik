package com.farmer.farmermanagement.dto;

import com.farmer.farmermanagement.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String country;
    private String state;
    private String pinCode;
    private String timeZone;
    private String message;  // ✅ Added optional message field

    // ✅ Convert Entity to DTO safely (Avoids NullPointerException)
    public static UserResponseDTO fromEntity(User user) {
        if (user == null) {
            return null; // Prevent NullPointerException
        }
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(Optional.ofNullable(user.getFirstName()).orElse(""))
                .lastName(Optional.ofNullable(user.getLastName()).orElse(""))
                .email(Optional.ofNullable(user.getEmail()).orElse(""))
                .phoneNumber(Optional.ofNullable(user.getPhoneNumber()).orElse(""))
                .dateOfBirth(Optional.ofNullable(user.getDateOfBirth()).orElse(null)) // Ensure it's LocalDate
                .gender(Optional.ofNullable(user.getGender()).orElse(""))
                .country(Optional.ofNullable(user.getCountry()).orElse(""))
                .state(Optional.ofNullable(user.getState()).orElse(""))
                .pinCode(Optional.ofNullable(user.getPinCode()).orElse(""))
                .timeZone(Optional.ofNullable(user.getTimeZone()).orElse(""))
                .message("")  // Default empty message
                .build();
    }

    // ✅ Overloaded method to include a message
    public static UserResponseDTO fromEntity(User user, String message) {
        UserResponseDTO dto = fromEntity(user);
        if (dto != null) {
            dto.setMessage(message);
        }
        return dto;
    }
}

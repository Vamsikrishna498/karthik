package com.farmer.farmermanagement.service;

import com.farmer.farmermanagement.dto.UserDTO;
import com.farmer.farmermanagement.entity.User;
import com.farmer.farmermanagement.exception.UserNotFoundException;
import com.farmer.farmermanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OtpService otpService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");
        user.setPassword("encodedPassword");
        user.setCountry("India");
        user.setState("Karnataka");
        user.setPinCode("560001");
        user.setTimeZone("IST");

        userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPhoneNumber("1234567890");
        userDTO.setPassword("plainPassword");
        userDTO.setCountry("India");
        userDTO.setState("Karnataka");
        userDTO.setPinCode("560001");
        userDTO.setTimeZone("IST");
    }

    @Test
    void testRegisterUser_Success() {
        when(userRepository.findByEmailOrPhoneNumber(anyString(), anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.registerUser(userDTO);

        assertNotNull(savedUser);
        assertEquals("John", savedUser.getFirstName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_AlreadyExists() {
        when(userRepository.findByEmailOrPhoneNumber(anyString(), anyString())).thenReturn(Optional.of(user));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(userDTO));

        assertEquals("User already exists with this email or phone number.", exception.getMessage());
    }

    @Test
    void testForgotPassword_Success() {
        when(userRepository.findByEmailOrPhoneNumber(anyString(), anyString())).thenReturn(Optional.of(user));
        when(otpService.generateAndSendOtp(anyString())).thenReturn("OTP Sent");

        String response = userService.forgotPassword("john.doe@example.com");

        assertEquals("OTP Sent", response);
        verify(otpService, times(1)).generateAndSendOtp(anyString());
    }

    @Test
    void testForgotPassword_UserNotFound() {
        when(userRepository.findByEmailOrPhoneNumber(anyString(), anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.forgotPassword("unknown@example.com"));
    }

    @Test
    void testForgotUserId_Success() {
        when(userRepository.findByEmailOrPhoneNumber(anyString(), anyString())).thenReturn(Optional.of(user));
        when(otpService.generateAndSendOtp(anyString())).thenReturn("OTP Sent");

        String response = userService.forgotUserId("1234567890");

        assertEquals("OTP Sent", response);
        verify(otpService, times(1)).generateAndSendOtp(anyString());
    }

    @Test
    void testForgotUserId_UserNotFound() {
        when(userRepository.findByEmailOrPhoneNumber(anyString(), anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.forgotUserId("9999999999"));
    }

    @Test
    void testVerifyOtpAndResetPassword_Success() {
        when(otpService.verifyOtp(anyString())).thenReturn(true);
        when(userRepository.findByEmailOrPhoneNumber(anyString(), anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(anyString())).thenReturn("newEncodedPassword");

        boolean result = userService.verifyOtpAndResetPassword("validToken", "john.doe@example.com", "newPassword");

        assertTrue(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testVerifyOtpAndResetPassword_InvalidOtp() {
        when(otpService.verifyOtp(anyString())).thenReturn(false);

        boolean result = userService.verifyOtpAndResetPassword("invalidToken", "john.doe@example.com", "newPassword");

        assertFalse(result);
    }

    @Test
    void testVerifyOtpAndResetPassword_UserNotFound() {
        when(otpService.verifyOtp(anyString())).thenReturn(true);
        when(userRepository.findByEmailOrPhoneNumber(anyString(), anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.verifyOtpAndResetPassword("validToken", "unknown@example.com", "newPassword"));
    }
}


package com.farmer.farmermanagement.service;

import com.farmer.farmermanagement.entity.Otp;
import com.farmer.farmermanagement.repository.OtpRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class OtpServiceTest {

    @Mock
    private FirebaseAuth firebaseAuth;

    @Mock
    private OtpRepository otpRepository;

    @InjectMocks
    private OtpService otpService;

    private String phoneNumber = "1234567890";
    private String otpCode = "123456";
    private String idToken = "sample_id_token";
    
    private Otp otpEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        otpEntity = Otp.builder()
                .emailOrPhone(phoneNumber)
                .otpCode(otpCode)
                .expiryTime(LocalDateTime.now().plusMinutes(10))
                .build();
    }

    @Test
    void generateAndSendOtpShouldReturnOtpCode() {
        String generatedOtp = "123456";

        String result = otpService.generateAndSendOtp(phoneNumber);

        assertNotNull(result);
        assertEquals(generatedOtp, result);
        verify(otpRepository).save(any(Otp.class));
    }

    @Test
    void verifyOtpShouldReturnTrueWhenOtpIsValid() throws FirebaseAuthException {
        when(firebaseAuth.verifyIdToken(idToken)).thenReturn(mock(FirebaseToken.class));
        when(otpRepository.findByEmailOrPhoneAndOtpCode(phoneNumber, otpCode)).thenReturn(Optional.of(otpEntity));

        boolean result = otpService.verifyOtp(idToken);

        assertTrue(result);
    }

    @Test
    void verifyOtpShouldReturnFalseWhenOtpIsInvalid() throws FirebaseAuthException {
        when(firebaseAuth.verifyIdToken(idToken)).thenReturn(mock(FirebaseToken.class));
        when(otpRepository.findByEmailOrPhoneAndOtpCode(phoneNumber, otpCode)).thenReturn(Optional.empty());

        boolean result = otpService.verifyOtp(idToken);

        assertFalse(result);
    }
}

package com.farmer.farmermanagement.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpService {

    private final FirebaseAuth firebaseAuth;

    public boolean verifyOtp(String idToken) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            log.info("OTP verification successful for user: {}", decodedToken.getEmail());
            return decodedToken != null;
        } catch (FirebaseAuthException e) {
            log.warn("OTP verification failed: {}", e.getMessage());
            return false;
        }
    }

    public String getUserEmailOrPhoneFromToken(String idToken) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            String emailOrPhone = decodedToken != null ? decodedToken.getEmail() : null;
            log.info("Extracted user identifier from token: {}", emailOrPhone);
            return emailOrPhone;
        } catch (FirebaseAuthException e) {
            log.error("Failed to extract user identifier from token: {}", e.getMessage());
            return null;
        }
    }

    public String generateAndSendOtp(String phoneNumber) {
        String otp = String.valueOf((int) (Math.random() * 1000000));
        log.info("Generated OTP for phone {}: {}", phoneNumber, otp);
        sendOtpThroughServerSide(phoneNumber, otp);
        return otp;
    }

    private void sendOtpThroughServerSide(String phoneNumber, String otp) {
        log.info("OTP sent to phone {}: {}", phoneNumber, otp);
    }
}

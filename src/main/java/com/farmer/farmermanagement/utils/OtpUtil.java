package com.farmer.farmermanagement.utils;

import org.springframework.stereotype.Component;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OtpUtil {

    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRATION_TIME = 5 * 60 * 1000; // 5 minutes in milliseconds
    private static final SecureRandom secureRandom = new SecureRandom();
    
    private final Map<String, OtpData> otpStorage = new ConcurrentHashMap<>();

    /**
     * Generates a secure OTP and stores it with an expiration time.
     * 
     * @param key User email or phone number.
     * @return Generated OTP.
     */
    public String generateOtp(String key) {
        String otp = String.format("%06d", secureRandom.nextInt(1000000)); // Ensures a 6-digit OTP
        otpStorage.put(key, new OtpData(otp, System.currentTimeMillis() + OTP_EXPIRATION_TIME));
        return otp;
    }

    /**
     * Verifies if the given OTP is correct and not expired.
     *
     * @param key User email or phone number.
     * @param otp OTP provided by the user.
     * @return True if OTP is valid, false otherwise.
     */
    public boolean verifyOtp(String key, String otp) {
        OtpData otpData = otpStorage.get(key);

        if (otpData == null || System.currentTimeMillis() > otpData.expiryTime) {
            otpStorage.remove(key); // Remove expired OTP
            return false;
        }

        boolean isValid = otpData.otp.equals(otp);
        if (isValid) {
            otpStorage.remove(key); // OTP used, remove it
        }

        return isValid;
    }

    /**
     * Inner class to store OTP details.
     */
    private static class OtpData {
        private final String otp;
        private final long expiryTime;

        public OtpData(String otp, long expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }
}


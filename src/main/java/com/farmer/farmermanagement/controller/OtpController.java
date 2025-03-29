package com.farmer.farmermanagement.controller;

import com.farmer.farmermanagement.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateOtp(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(otpService.generateAndSendOtp(phoneNumber));
    }
}

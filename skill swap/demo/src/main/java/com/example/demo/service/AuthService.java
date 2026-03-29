package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.PendingUser;
import com.example.demo.entity.User;
import com.example.demo.repository.PendingUserRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PendingUserRepository pendingUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final OtpUtil otpUtil;
    private final EmailService emailService;

    @Value("${app.otp.expiry-minutes}")
    private long otpExpiryMinutes;

    public ApiResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        if (pendingUserRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("OTP already sent to this email. Please verify first.");
        }

        String otp = otpUtil.generateOtp();

        PendingUser pendingUser = PendingUser.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .otp(otp)
                .otpExpiry(LocalDateTime.now().plusMinutes(otpExpiryMinutes))
                .createdAt(LocalDateTime.now())
                .build();

        pendingUserRepository.save(pendingUser);

        try {
            emailService.sendOtpEmail(pendingUser.getEmail(), otp);
        } catch (Exception e) {
            pendingUserRepository.delete(pendingUser);
            throw new RuntimeException("OTP email could not be sent. Please try again.");
        }

        return new ApiResponse("OTP sent to email. Please verify to complete registration.");
    }

    public ApiResponse verifyOtp(OtpVerifyRequest request) {
        PendingUser pendingUser = pendingUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("No pending registration found for this email"));

        if (!pendingUser.getOtp().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        if (pendingUser.getOtpExpiry() == null || pendingUser.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        User user = User.builder()
                .fullName(pendingUser.getFullName())
                .email(pendingUser.getEmail())
                .password(pendingUser.getPassword())
                .phone(pendingUser.getPhone())
                .bio("")
                .location("")
                .role("ROLE_USER")
                .verified(true)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        pendingUserRepository.delete(pendingUser);

        return new ApiResponse("OTP verified successfully. Registration completed.");
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, "Login successful");
    }
}
package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "swappusers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 15)
    private String phone;

    @Column(length = 255)
    private String bio;

    @Column(length = 100)
    private String location;

    @Column(name ="user_role", nullable = false, length = 20)
    private String role;

    private Boolean verified;

    @Column(length = 6)
    private String otp;

    private LocalDateTime otpExpiry;

    private LocalDateTime createdAt;
}
package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pending_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PendingUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pending_user_seq_gen")
    @SequenceGenerator(name = "pending_user_seq_gen", sequenceName = "pending_user_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 15)
    private String phone;

    @Column(length = 6)
    private String otp;

    private LocalDateTime otpExpiry;

    private LocalDateTime createdAt;
}
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "swap_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SwapRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "swap_seq_gen")
    @SequenceGenerator(name = "swap_seq_gen", sequenceName = "swap_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private String offeredSkill;
    private String wantedSkill;
    private String message;
    private String status;
    private LocalDateTime createdAt;
}
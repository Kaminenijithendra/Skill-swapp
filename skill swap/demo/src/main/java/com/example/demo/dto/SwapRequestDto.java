package com.example.demo.dto;


import lombok.Data;

@Data
public class SwapRequestDto {
    private Long receiverId;
    private String offeredSkill;
    private String wantedSkill;
    private String message;
}
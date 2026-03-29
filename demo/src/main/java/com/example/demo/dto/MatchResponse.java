package com.example.demo.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResponse {
    private Long userId;
    private String fullName;
    private String offeredSkill;
    private String wantedSkill;
    private String location;
}
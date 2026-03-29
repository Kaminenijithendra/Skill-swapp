package com.example.demo.dto;

import lombok.Data;

@Data
public class AddSkillRequest {
    private String skillName;
    private String category;
    private String level;
}
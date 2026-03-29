package com.example.demo.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String bio;
    private String location;
    private Boolean verified;
}
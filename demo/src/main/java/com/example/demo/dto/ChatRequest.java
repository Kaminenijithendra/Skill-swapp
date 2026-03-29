package com.example.demo.dto;


import lombok.Data;

@Data
public class ChatRequest {
    private Long receiverId;
    private String message;
}

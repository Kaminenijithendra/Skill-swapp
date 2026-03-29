package com.example.demo.Controllers;



import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.ChatRequest;
import com.example.demo.entity.ChatMessage;
import com.example.demo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendMessage(@RequestBody ChatRequest request) {
        return ResponseEntity.ok(new ApiResponse(chatService.sendMessage(request)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ChatMessage>> getConversation(@PathVariable Long userId) {
        return ResponseEntity.ok(chatService.getConversation(userId));
    }
}
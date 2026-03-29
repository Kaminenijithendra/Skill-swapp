package com.example.demo.service;


import com.example.demo.dto.ChatRequest;
import com.example.demo.entity.ChatMessage;
import com.example.demo.entity.User;
import com.example.demo.repository.ChatMessageRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    public String sendMessage(ChatRequest request) {
        User sender = getCurrentUser();
        User receiver = userRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        ChatMessage message = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .message(request.getMessage())
                .sentAt(LocalDateTime.now())
                .build();

        chatMessageRepository.save(message);
        return "Message sent successfully";
    }

    public List<ChatMessage> getConversation(Long userId) {
        User currentUser = getCurrentUser();
        User otherUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return chatMessageRepository.findBySenderAndReceiverOrReceiverAndSenderOrderBySentAtAsc(
                currentUser, otherUser, currentUser, otherUser
        );
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

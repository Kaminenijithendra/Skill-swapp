package com.example.demo.repository;


import com.example.demo.entity.ChatMessage;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySenderAndReceiverOrReceiverAndSenderOrderBySentAtAsc(
            User sender, User receiver, User receiver2, User sender2
    );
}
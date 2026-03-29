package com.example.demo.service;


import com.example.demo.dto.SwapRequestDto;
import com.example.demo.entity.SwapRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.SwapRequestRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SwapService {

    private final SwapRequestRepository swapRequestRepository;
    private final UserRepository userRepository;

    public String sendRequest(SwapRequestDto dto) {
        User sender = getCurrentUser();
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        SwapRequest request = SwapRequest.builder()
                .sender(sender)
                .receiver(receiver)
                .offeredSkill(dto.getOfferedSkill())
                .wantedSkill(dto.getWantedSkill())
                .message(dto.getMessage())
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        swapRequestRepository.save(request);
        return "Swap request sent successfully";
    }

    public String acceptRequest(Long id) {
        SwapRequest request = swapRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Swap request not found"));
        request.setStatus("ACCEPTED");
        swapRequestRepository.save(request);
        return "Swap request accepted";
    }

    public String rejectRequest(Long id) {
        SwapRequest request = swapRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Swap request not found"));
        request.setStatus("REJECTED");
        swapRequestRepository.save(request);
        return "Swap request rejected";
    }

    public List<SwapRequest> mySentRequests() {
        return swapRequestRepository.findBySender(getCurrentUser());
    }

    public List<SwapRequest> myReceivedRequests() {
        return swapRequestRepository.findByReceiver(getCurrentUser());
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
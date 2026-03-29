package com.example.demo.repository;

import com.example.demo.entity.SwapRequest;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SwapRequestRepository extends JpaRepository<SwapRequest, Long> {
    List<SwapRequest> findBySender(User sender);
    List<SwapRequest> findByReceiver(User receiver);
}
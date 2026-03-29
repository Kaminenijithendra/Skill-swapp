package com.example.demo.repository;

import com.example.demo.entity.PendingUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PendingUserRepository extends JpaRepository<PendingUser, Long> {
    Optional<PendingUser> findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}
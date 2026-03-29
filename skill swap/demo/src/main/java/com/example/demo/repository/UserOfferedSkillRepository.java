package com.example.demo.repository;


import com.example.demo.entity.User;
import com.example.demo.entity.UserOfferedSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOfferedSkillRepository extends JpaRepository<UserOfferedSkill, Long> {
    List<UserOfferedSkill> findByUser(User user);
}
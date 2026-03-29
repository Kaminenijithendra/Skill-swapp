package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserWantedSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserWantedSkillRepository extends JpaRepository<UserWantedSkill, Long> {
    List<UserWantedSkill> findByUser(User user);
}
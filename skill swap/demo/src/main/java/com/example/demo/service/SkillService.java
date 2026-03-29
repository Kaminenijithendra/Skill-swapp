package com.example.demo.service;

import com.example.demo.dto.AddSkillRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;
    private final UserOfferedSkillRepository userOfferedSkillRepository;
    private final UserWantedSkillRepository userWantedSkillRepository;

    public String addOfferedSkill(AddSkillRequest request) {
        User user = getCurrentUser();

        Skill skill = skillRepository.findByNameIgnoreCase(request.getSkillName())
                .orElseGet(() -> skillRepository.save(
                        Skill.builder()
                                .name(request.getSkillName())
                                .category(request.getCategory())
                                .build()
                ));

        UserOfferedSkill offeredSkill = UserOfferedSkill.builder()
                .user(user)
                .skill(skill)
                .level(request.getLevel())
                .build();

        userOfferedSkillRepository.save(offeredSkill);
        return "Offered skill added successfully";
    }

    public String addWantedSkill(AddSkillRequest request) {
        User user = getCurrentUser();

        Skill skill = skillRepository.findByNameIgnoreCase(request.getSkillName())
                .orElseGet(() -> skillRepository.save(
                        Skill.builder()
                                .name(request.getSkillName())
                                .category(request.getCategory())
                                .build()
                ));

        UserWantedSkill wantedSkill = UserWantedSkill.builder()
                .user(user)
                .skill(skill)
                .desiredLevel(request.getLevel())
                .build();

        userWantedSkillRepository.save(wantedSkill);
        return "Wanted skill added successfully";
    }

    public List<UserOfferedSkill> getMyOfferedSkills() {
        return userOfferedSkillRepository.findByUser(getCurrentUser());
    }

    public List<UserWantedSkill> getMyWantedSkills() {
        return userWantedSkillRepository.findByUser(getCurrentUser());
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
package com.example.demo.service;



import com.example.demo.dto.MatchResponse;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final UserRepository userRepository;
    private final UserOfferedSkillRepository offeredSkillRepository;
    private final UserWantedSkillRepository wantedSkillRepository;

    public List<MatchResponse> findMatches() {
        User currentUser = getCurrentUser();

        List<UserOfferedSkill> myOffered = offeredSkillRepository.findByUser(currentUser);
        List<UserWantedSkill> myWanted = wantedSkillRepository.findByUser(currentUser);

        List<User> allUsers = userRepository.findAll();
        List<MatchResponse> matches = new ArrayList<>();

        for (User other : allUsers) {
            if (other.getId().equals(currentUser.getId())) continue;

            List<UserOfferedSkill> otherOffered = offeredSkillRepository.findByUser(other);
            List<UserWantedSkill> otherWanted = wantedSkillRepository.findByUser(other);

            for (UserWantedSkill myWant : myWanted) {
                for (UserOfferedSkill otherOffer : otherOffered) {
                    if (myWant.getSkill().getName().equalsIgnoreCase(otherOffer.getSkill().getName())) {
                        for (UserOfferedSkill myOffer : myOffered) {
                            for (UserWantedSkill otherWant : otherWanted) {
                                if (myOffer.getSkill().getName().equalsIgnoreCase(otherWant.getSkill().getName())) {
                                    matches.add(MatchResponse.builder()
                                            .userId(other.getId())
                                            .fullName(other.getFullName())
                                            .offeredSkill(otherOffer.getSkill().getName())
                                            .wantedSkill(otherWant.getSkill().getName())
                                            .location(other.getLocation())
                                            .build());
                                }
                            }
                        }
                    }
                }
            }
        }

        return matches;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
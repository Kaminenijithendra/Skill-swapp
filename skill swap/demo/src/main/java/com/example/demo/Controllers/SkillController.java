package com.example.demo.Controllers;

import com.example.demo.dto.AddSkillRequest;
import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.UserOfferedSkill;
import com.example.demo.entity.UserWantedSkill;
import com.example.demo.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping("/offered")
    public ResponseEntity<ApiResponse> addOfferedSkill(@RequestBody AddSkillRequest request) {
        return ResponseEntity.ok(new ApiResponse(skillService.addOfferedSkill(request)));
    }

    @PostMapping("/wanted")
    public ResponseEntity<ApiResponse> addWantedSkill(@RequestBody AddSkillRequest request) {
        return ResponseEntity.ok(new ApiResponse(skillService.addWantedSkill(request)));
    }

    @GetMapping("/offered")
    public ResponseEntity<List<UserOfferedSkill>> getMyOfferedSkills() {
        return ResponseEntity.ok(skillService.getMyOfferedSkills());
    }

    @GetMapping("/wanted")
    public ResponseEntity<List<UserWantedSkill>> getMyWantedSkills() {
        return ResponseEntity.ok(skillService.getMyWantedSkills());
    }
}
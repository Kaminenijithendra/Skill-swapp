package com.example.demo.Controllers;

import com.example.demo.dto.MatchResponse;
import com.example.demo.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping
    public ResponseEntity<List<MatchResponse>> getMatches() {
        return ResponseEntity.ok(matchService.findMatches());
    }
}
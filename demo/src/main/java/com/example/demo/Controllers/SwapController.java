package com.example.demo.Controllers;


import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.SwapRequestDto;
import com.example.demo.entity.SwapRequest;
import com.example.demo.service.SwapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/swaps")
@RequiredArgsConstructor
public class SwapController {

    private final SwapService swapService;

    @PostMapping("/request")
    public ResponseEntity<ApiResponse> sendRequest(@RequestBody SwapRequestDto dto) {
        return ResponseEntity.ok(new ApiResponse(swapService.sendRequest(dto)));
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<ApiResponse> acceptRequest(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse(swapService.acceptRequest(id)));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<ApiResponse> rejectRequest(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse(swapService.rejectRequest(id)));
    }

    @GetMapping("/sent")
    public ResponseEntity<List<SwapRequest>> mySentRequests() {
        return ResponseEntity.ok(swapService.mySentRequests());
    }

    @GetMapping("/received")
    public ResponseEntity<List<SwapRequest>> myReceivedRequests() {
        return ResponseEntity.ok(swapService.myReceivedRequests());
    }
}
package com.example.demo.Controllers;


import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.ReviewRequest;
import com.example.demo.entity.Review;
import com.example.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResponse> addReview(@RequestBody ReviewRequest request) {
        return ResponseEntity.ok(new ApiResponse(reviewService.addReview(request)));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsOfUser(userId));
    }
}
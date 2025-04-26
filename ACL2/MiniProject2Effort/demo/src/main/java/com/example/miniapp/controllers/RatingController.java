package com.example.miniapp.controllers;

import com.example.miniapp.models.Rating;
import com.example.miniapp.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/addRating")
    public ResponseEntity<?> addRating(@Valid @RequestBody Rating rating) {
        try {
            return ResponseEntity.ok(ratingService.addRating(rating));
        } catch (InvalidDataAccessApiUsageException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRating(@PathVariable String id, @RequestBody Rating rating) {
        try {
            return ResponseEntity.ok(ratingService.updateRating(id, rating));
        } catch (InvalidDataAccessApiUsageException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable String id) {
        ratingService.deleteRating(id);
        return ResponseEntity.ok("Rating deleted successfully.");
    }

    @GetMapping("/findByEntity")
    public ResponseEntity<?> findRatingsByEntity(@RequestParam Long entityId, @RequestParam String entityType) {
        try {
            return ResponseEntity.ok(ratingService.getRatingsByEntity(entityId, entityType));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/findAboveScore")
    public ResponseEntity<?> findRatingsAboveScore(@RequestParam int minScore) {
        try {
            return ResponseEntity.ok(ratingService.findRatingsAboveScore(minScore));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }
}
package com.example.miniapp.controllers;

import com.example.miniapp.models.Rating;
import com.example.miniapp.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/addRating")
    public Rating addRating(@RequestBody Rating rating) {
        return ratingService.addRating(rating);
    }

    @PutMapping("/update/{id}")
    public Rating updateRating(@PathVariable String id, @RequestBody Rating rating) {
        return ratingService.updateRating(id, rating);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRating(@PathVariable String id) {
        ratingService.deleteRating(id);
        return "Rating deleted successfully.";
    }

    @GetMapping("/findByEntity")
    public List<Rating> findByEntity(@RequestParam Long entityId, @RequestParam String entityType) {
        return ratingService.getRatingsByEntity(entityId, entityType);
    }

    @GetMapping("/findAboveScore")
    public List<Rating> findAboveScore(@RequestParam int minScore) {
        return ratingService.findRatingsAboveScore(minScore);
    }
}

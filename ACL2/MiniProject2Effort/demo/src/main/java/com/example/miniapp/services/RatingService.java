package com.example.miniapp.services;

import com.example.miniapp.models.Rating;
import com.example.miniapp.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating addRating(Rating rating) {
        if (rating == null) {
            throw new InvalidDataAccessApiUsageException("Rating must not be null");
        }
        if (rating.getEntityId() == null || rating.getEntityType() == null || rating.getEntityType().isBlank()) {
            throw new IllegalArgumentException("Entity information is required");
        }
        if (rating.getScore() == null || rating.getScore() < 1 || rating.getScore() > 5) {
            throw new IllegalArgumentException("Rating score must be between 1 and 5");
        }
        if (rating.getRatingDate() == null) {
            throw new IllegalArgumentException("Rating date is required");
        }

        return ratingRepository.save(rating);
    }

    public Rating updateRating(String id, Rating updatedRating) {
        if (updatedRating == null || id == null) {
            throw new InvalidDataAccessApiUsageException("Invalid update input");
        }
        updatedRating.setId(id);
        return ratingRepository.save(updatedRating);
    }

    public void deleteRating(String id) {
        if (id != null && ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
        }
    }

    public List<Rating> getRatingsByEntity(Long entityId, String entityType) {
        if (entityId == null || entityType == null || entityType.isBlank()) {
            throw new IllegalArgumentException("Invalid entity lookup input");
        }
        return ratingRepository.findByEntityIdAndEntityType(entityId, entityType);
    }

    public List<Rating> findRatingsAboveScore(int minScore) {
        if (minScore < 1 || minScore > 5) {
            throw new IllegalArgumentException("Rating threshold must be between 1 and 5");
        }
        return ratingRepository.findByScoreGreaterThan(minScore);
    }
}
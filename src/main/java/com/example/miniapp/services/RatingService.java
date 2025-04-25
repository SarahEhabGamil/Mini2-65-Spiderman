package com.example.miniapp.services;

import com.example.miniapp.models.Rating;
import com.example.miniapp.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating addRating(Rating rating) {
     return ratingRepository.save(rating);
    }

    public Rating updateRating(String id, Rating updatedRating) {
        return ratingRepository.findById(id)
                .map(existing -> {
                    existing.setScore(updatedRating.getScore());
                    existing.setComment(updatedRating.getComment());
                    existing.setRatingDate(updatedRating.getRatingDate());
                    return ratingRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Rating with ID " + id + " not found"));
    }


    public void deleteRating(String id) {
        Optional<Rating> existingRating = ratingRepository.findById(id);
        existingRating.ifPresent(ratingRepository::delete);

    }

    public List<Rating> getRatingsByEntity(Long entityId, String entityType) {
        return ratingRepository.findByEntityIdAndEntityType(entityId, entityType);
    }

    public List<Rating> findRatingsAboveScore(int minScore) {
        return ratingRepository.findByScoreGreaterThan(minScore);
    }

}

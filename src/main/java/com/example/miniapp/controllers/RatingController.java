package com.example.miniapp.controllers;

import com.example.miniapp.models.Rating;
import com.example.miniapp.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> updateRating(@PathVariable String id, @RequestBody Rating updatedRating) {
        try {
            Rating result = ratingService.updateRating(id, updatedRating);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public String deleteRating(@PathVariable String id) {
        ratingService.deleteRating(id);
        return "Rating with ID " + id + " has been deleted.";
    }

    @GetMapping("/findByEntity")
    public List<Rating> findRatingsByEntity(@RequestParam Long entityId, @RequestParam String entityType) {
        return ratingService.getRatingsByEntity(entityId, entityType);
    }

    @GetMapping("/findAboveScore")
    public List<Rating> findRatingsAboveScore(@RequestParam int minScore) {
        return ratingService.findRatingsAboveScore(minScore);
    }

}

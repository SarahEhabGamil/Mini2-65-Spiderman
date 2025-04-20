package com.example.miniapp.repositories;


import org.springframework.stereotype.Repository;
import com.example.miniapp.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface CaptainRepository  extends JpaRepository<Captain, Long> {
    @Query(value = "SELECT * FROM captains WHERE avg_rating_score > :ratingThreshold", nativeQuery = true)
    List<Captain> findByAvgRatingScoreGreaterThan(Double ratingThreshold);

    @Query(value = "SELECT * FROM captains WHERE license_number = :licenseNumber LIMIT 1", nativeQuery = true)
    Optional<Captain> findByLicenseNumber(String licenseNumber);
}

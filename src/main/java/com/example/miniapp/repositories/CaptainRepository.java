package com.example.miniapp.repositories;


import org.springframework.stereotype.Repository;
import com.example.miniapp.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface CaptainRepository  extends JpaRepository<Captain, Long> {
    List<Captain> findByAvgRatingScoreGreaterThan(Double ratingThreshold);

    Optional<Captain> findByLicenseNumber(String licenseNumber);

}

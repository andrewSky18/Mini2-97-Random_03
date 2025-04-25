package com.example.miniapp.repositories;

import com.example.miniapp.models.Captain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaptainRepository extends JpaRepository<Captain, Long> {
    List<Captain> findByAvgRatingScoreGreaterThan(Double threshold);
    Captain findByLicenseNumber(String licenseNumber);
}

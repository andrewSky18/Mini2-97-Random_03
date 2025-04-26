package com.example.miniapp.services;

import com.example.miniapp.models.Captain;
import com.example.miniapp.repositories.CaptainRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaptainService {

    private final CaptainRepository captainRepository;

    @Autowired
    public CaptainService(CaptainRepository captainRepository) {
        this.captainRepository = captainRepository;
    }


    public Captain addCaptain(Captain captain) {
        if (captain == null) {
            throw new InvalidDataAccessApiUsageException("Captain must not be null");
        }

        if (captainRepository.findByLicenseNumber(captain.getLicenseNumber()) != null) {
            throw new IllegalArgumentException("Captain with this license number already exists");
        }

        return captainRepository.save(captain);
    }

    public List<Captain> getAllCaptains() {
        return captainRepository.findAll();
    }

    public Captain getCaptainById(Long id) {
        return captainRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Captain not found with ID: " + id));
    }

    public List<Captain> getCaptainsByRating(Double ratingThreshold) {
        return captainRepository.findByAvgRatingScoreGreaterThan(ratingThreshold);
    }

    public Captain getCaptainByLicenseNumber(String licenseNumber) {
        Captain captain = captainRepository.findByLicenseNumber(licenseNumber);
        if (captain == null) {
            return null;
        }
        return captain;
    }
}
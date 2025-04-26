package com.example.miniapp.controllers;

import com.example.miniapp.models.Captain;
import com.example.miniapp.services.CaptainService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/captain")
public class CaptainController {

    private final CaptainService captainService;

    @Autowired
    public CaptainController(CaptainService captainService) {
        this.captainService = captainService;
    }

    @PostMapping("/addCaptain")
    public ResponseEntity<?> addCaptain(@RequestBody Captain captain) {
        try {
            return ResponseEntity.ok(captainService.addCaptain(captain)); // 200 OK as expected by test
        } catch (InvalidDataAccessApiUsageException | IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/allCaptains")
    public ResponseEntity<List<Captain>> getAllCaptains() {
        return ResponseEntity.ok(captainService.getAllCaptains());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCaptainById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(captainService.getCaptainById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/filterByRating")
    public ResponseEntity<List<Captain>> getCaptainsByRating(@RequestParam Double ratingThreshold) {
        return ResponseEntity.ok(captainService.getCaptainsByRating(ratingThreshold));
    }

    @GetMapping("/filterByLicenseNumber")
    public Captain getCaptainByLicenseNumber(@RequestParam String licenseNumber) {
        return captainService.getCaptainByLicenseNumber(licenseNumber);
    }
}
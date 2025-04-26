package com.example.miniapp.controllers;

import com.example.miniapp.models.Trip;
import com.example.miniapp.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/addTrip")
    public ResponseEntity<?> addTrip(@RequestBody Trip trip) {
        try {
            return ResponseEntity.ok(tripService.addTrip(trip));
        } catch (InvalidDataAccessApiUsageException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/allTrips")
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getTripById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        try {
            Trip updated = tripService.updateTrip(id, trip);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trip not found.");
            }
            return ResponseEntity.ok(updated);
        } catch (InvalidDataAccessApiUsageException | IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.ok("Trip deleted successfully.");
    }

    @GetMapping("/findByDateRange")
    public ResponseEntity<?> findTripsWithinDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        try {
            return ResponseEntity.ok(tripService.findTripsWithinDateRange(startDate, endDate));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/findByCaptainId")
    public ResponseEntity<?> findTripsByCaptainId(@RequestParam Long captainId) {
        try {
            return ResponseEntity.ok(tripService.findTripsByCaptainId(captainId));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
    }
}
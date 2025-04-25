package com.example.miniapp.controllers;

import com.example.miniapp.models.Trip;
import com.example.miniapp.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/addTrip")
    public Trip addTrip(@RequestBody Trip trip) {
        return tripService.addTrip(trip);
    }

    @GetMapping("/allTrips")
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    @PutMapping("/update/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        return tripService.updateTrip(id, trip);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return "Trip deleted successfully.";
    }

    // Find trips within date range
    @GetMapping("/findByDateRange")
    public List<Trip> findTripsWithinDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return tripService.findTripsWithinDateRange(startDate, endDate);
    }

    @GetMapping("/findByCaptainId")
    public List<Trip> findByCaptainId(@RequestParam Long captainId) {
        return tripService.findTripsByCaptainId(captainId);
    }
}

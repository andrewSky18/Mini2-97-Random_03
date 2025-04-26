package com.example.miniapp.services;

import com.example.miniapp.models.Trip;
import com.example.miniapp.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip addTrip(Trip trip) {
        if (trip == null) {
            throw new InvalidDataAccessApiUsageException("Trip must not be null");
        }
        if (trip.getTripDate() == null || trip.getOrigin() == null || trip.getDestination() == null) {
            throw new IllegalArgumentException("Trip date, origin, and destination are required");
        }
        if (trip.getTripCost() == null || trip.getTripCost() < 0) {
            throw new IllegalArgumentException("Trip cost must be non-negative");
        }

        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(Long id) {
        if (id == null) return null;
        return tripRepository.findById(id).orElse(null);
    }

    public Trip updateTrip(Long id, Trip updatedTrip) {
        if (updatedTrip == null) {
            throw new InvalidDataAccessApiUsageException("Trip must not be null");
        }
        if (!tripRepository.existsById(id)) {
            return null;
        }

        updatedTrip.setId(id);
        return tripRepository.save(updatedTrip);
    }

    public void deleteTrip(Long id) {
        if (id != null && tripRepository.existsById(id)) {
            tripRepository.deleteById(id);
        }
    }

    public List<Trip> findTripsWithinDateRange(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null || end.isBefore(start)) {
            throw new IllegalArgumentException("Invalid date range");
        }
        return tripRepository.findByTripDateBetween(start, end);
    }

    public List<Trip> findTripsByCaptainId(Long captainId) {
        if (captainId == null) {
            throw new IllegalArgumentException("Captain ID must not be null");
        }
        return tripRepository.findByCaptainId(captainId);
    }
}
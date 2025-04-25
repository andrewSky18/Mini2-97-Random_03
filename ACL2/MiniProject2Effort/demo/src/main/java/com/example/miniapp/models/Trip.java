package com.example.miniapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private LocalDateTime tripDate;

    @NonNull
    private String origin;

    @NonNull
    private String destination;

    @NonNull
    private Double tripCost;

    @ManyToOne
    @JoinColumn(name = "captain_id")
    private Captain captain;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "trip", cascade = CascadeType.ALL)
    private Payment payment;
}

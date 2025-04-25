package com.example.miniapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "captains")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Captain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(unique = true)
    private String licenseNumber;

    private Double avgRatingScore;

    @OneToMany(mappedBy = "captain", cascade = CascadeType.ALL)
    private List<Trip> trips;
}

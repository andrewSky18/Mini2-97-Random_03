package com.example.miniapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Trip> trips;
}

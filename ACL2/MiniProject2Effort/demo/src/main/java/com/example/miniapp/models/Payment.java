package com.example.miniapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Double amount;

    @NonNull
    private String paymentMethod;

    @NonNull
    private Boolean paymentStatus;

    @OneToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;
}

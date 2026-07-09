package com.example.tripnode.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Column(nullable = false)
    private Double pickupLocationLatitude;

    @Column(nullable = false)
    private Double pickupLocationLongitude;

    private String dropOffLocation;

    private BigDecimal fare;

    private LocalDateTime scheduledPickupTime;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private BookingStatus status = BookingStatus.PENDING;

    public enum BookingStatus{
        PENDING,
        CANCELLED,
        CONFIRMED,
        COMPLETED
    }
}
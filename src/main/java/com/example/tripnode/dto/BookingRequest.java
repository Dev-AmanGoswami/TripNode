package com.example.tripnode.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class BookingRequest {
    @NotNull(message = "Passenger ID is required")
    private Long passengerId;

    private Long driverId;

    private Double pickUpLocationLatitude;

    private Double pickUpLocationLongitude;

    @NotBlank(message = "DropOff location is required")
    private String dropOffLocation;

    @NotNull(message = "Fare is required")
    @Positive(message = "Fare must be positive")
    private BigDecimal fare;

    private LocalDateTime scheduledPickUpTime;
}

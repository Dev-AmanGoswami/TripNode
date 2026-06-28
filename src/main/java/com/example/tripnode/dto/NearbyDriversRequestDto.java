package com.example.tripnode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearbyDriversRequestDto {
    private Double latitude;
    private Double longitude;
    private Double radius;
}

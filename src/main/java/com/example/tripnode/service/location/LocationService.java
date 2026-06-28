package com.example.tripnode.service.location;

import com.example.tripnode.dto.DriverLocationDto;

import java.util.List;

public interface LocationService {
    Boolean saveDriverLocation(String driverId, Double latitude, Double longitude);
    List<DriverLocationDto> getNearbyDrivers(Double latitude, Double longitude, Double radius);
}

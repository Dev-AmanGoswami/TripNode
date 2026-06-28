package com.example.tripnode.controller;

import com.example.tripnode.dto.DriverLocationDto;
import com.example.tripnode.dto.NearbyDriversRequestDto;
import com.example.tripnode.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping("/driverLocation")
    public ResponseEntity<Boolean> saveDriverLocation(@RequestBody DriverLocationDto driverLocationDto){
        Boolean saved = locationService.saveDriverLocation(driverLocationDto.getDriverId(), driverLocationDto.getLatitude(), driverLocationDto.getLongitude());
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/nearbyDrivers")
    public ResponseEntity<List<DriverLocationDto>> getNearbyDrivers(@RequestBody NearbyDriversRequestDto request){
        List<DriverLocationDto> nearbyDrivers = locationService.getNearbyDrivers(
                request.getLatitude(),
                request.getLongitude(),
                request.getRadius()
        );
        return ResponseEntity.ok(nearbyDrivers);
    }
}

package com.example.tripnode.service.implementation;

import com.example.tripnode.dto.DriverLocation;
import com.example.tripnode.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisLocationServiceImpl implements LocationService {

    private static final String DRIVER_GEO_OPS_KEY = "driver:geo";

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public Boolean saveDriverLocation(String driverId, Double latitude, Double longitude) {
        GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
        geoOperations.add(
                DRIVER_GEO_OPS_KEY,
                new RedisGeoCommands.GeoLocation<>(driverId, new Point(latitude, longitude))
        );

        return true;
    }

    @Override
    public List<DriverLocation> getNearbyDrivers(Double latitude, Double longitude, Double radius) {
        GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
        Distance circleRadius = new Distance(radius, Metrics.KILOMETERS);

        Circle circle = new Circle(new Point(latitude, longitude), radius);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOperations.radius(DRIVER_GEO_OPS_KEY, circle);

        List<DriverLocation> driverLocations = new ArrayList<>();
        for(GeoResult<RedisGeoCommands.GeoLocation<String>> result: results.getContent()){
            Point point = geoOperations.position(DRIVER_GEO_OPS_KEY, result.getContent().getName()).get(0);
            DriverLocation driverLocation = DriverLocation.builder()
                    .driverId(result.getContent().getName())
                    .latitude(point.getY())
                    .longitude(point.getX())
                    .build();

            driverLocations.add(driverLocation);
        }

        return driverLocations;
    }
}

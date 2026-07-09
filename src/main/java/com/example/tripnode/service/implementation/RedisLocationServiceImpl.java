package com.example.tripnode.service.implementation;

import com.example.tripnode.dto.DriverLocationDto;
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
    public Boolean saveDriverLocation(Long driverId, Double latitude, Double longitude) {
        GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
        geoOperations.add(
                DRIVER_GEO_OPS_KEY,
                new RedisGeoCommands.GeoLocation<>(driverId.toString(), new Point(longitude, latitude))
        );

        return true;
    }

    @Override
    public List<DriverLocationDto> getNearbyDrivers(Double latitude, Double longitude, Double radius) {
        GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
        Distance circleRadius = new Distance(radius, Metrics.KILOMETERS);

        Circle circle = new Circle(new Point(longitude, latitude), circleRadius);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOperations.radius(DRIVER_GEO_OPS_KEY, circle);

        List<DriverLocationDto> driverLocationDtos = new ArrayList<>();
        for(GeoResult<RedisGeoCommands.GeoLocation<String>> result: results.getContent()){
            Point point = geoOperations.position(DRIVER_GEO_OPS_KEY, result.getContent().getName()).get(0);
            DriverLocationDto driverLocationDto = DriverLocationDto.builder()
                    .driverId(Long.parseLong(result.getContent().getName()))
                    .latitude(point.getY())
                    .longitude(point.getX())
                    .build();

            driverLocationDtos.add(driverLocationDto);
        }

        return driverLocationDtos;
    }
}

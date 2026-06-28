package com.example.tripnode.mapper;

import com.example.tripnode.dto.DriverResponse;
import com.example.tripnode.entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
    public DriverResponse toResponse(Driver driver){
        return DriverResponse.builder()
                .id(driver.getId())
                .name(driver.getName())
                .email(driver.getEmail())
                .phoneNumber(driver.getPhoneNumber())
                .licenseNumber(driver.getLicenseNumber())
                .vehicleModel(driver.getVehicleModel())
                .vehiclePlateNumber(driver.getVehiclePlateNumber())
                .isAvailable(driver.getIsAvailable())
                .createdAt(driver.getCreatedAt())
                .updatedAt(driver.getUpdatedAt())
                .build();
    }
}

package com.example.tripnode.service.implementation;

import com.example.tripnode.dto.DriverRequest;
import com.example.tripnode.dto.DriverResponse;
import com.example.tripnode.entity.Driver;
import com.example.tripnode.mapper.DriverMapper;
import com.example.tripnode.repository.DriverRepository;
import com.example.tripnode.service.driver.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public Optional<DriverResponse> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<DriverResponse> findAll() {
        return List.of();
    }

    @Override
    public Optional<DriverResponse> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<DriverResponse> findAvailableDrivers() {
        return List.of();
    }

    @Override
    public DriverResponse create(DriverRequest request) {
        Driver driver = driverRepository.save(
            Driver.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .licenseNumber(request.getLicenseNumber())
                    .vehicleModel(request.getVehicleModel())
                    .vehiclePlateNumber(request.getVehiclePlateNumber())
                    .isAvailable(request.getIsAvailable())
                    .build()
        );

        return driverMapper.toResponse(driver);
    }

    @Override
    public DriverResponse update(Long id, DriverRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

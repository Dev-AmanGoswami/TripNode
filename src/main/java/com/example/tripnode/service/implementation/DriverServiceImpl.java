package com.example.tripnode.service.implementation;

import com.example.tripnode.dto.DriverRequest;
import com.example.tripnode.dto.DriverResponse;
import com.example.tripnode.service.driver.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
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
        return null;
    }

    @Override
    public DriverResponse update(Long id, DriverRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

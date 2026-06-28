package com.example.tripnode.service.driver;

import com.example.tripnode.dto.DriverRequest;
import com.example.tripnode.dto.DriverResponse;

public interface DriverWriteService {
    DriverResponse create(DriverRequest request);
    DriverResponse update(Long id, DriverRequest request);
    void deleteById(Long id);
}

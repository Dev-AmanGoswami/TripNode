package com.example.tripnode.service.passenger;

import com.example.tripnode.dto.PassengerRequest;
import com.example.tripnode.dto.PassengerResponse;

public interface PassengerWriteService {
    PassengerResponse create(PassengerRequest request);
    PassengerResponse update(Long id, PassengerRequest request);
    void deleteById(Long id);
}

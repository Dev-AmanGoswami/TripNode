package com.example.tripnode.service.passenger;

import com.example.tripnode.entity.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerReadService {
    List<Passenger> findAll();
    Optional<Passenger> findById(Long id);
    List<Passenger> findByEmail(String email);
}

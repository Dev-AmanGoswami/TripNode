package com.example.tripnode.service.booking;

import com.example.tripnode.dto.BookingResponse;

import java.util.List;
import java.util.Optional;

public interface BookingReadService {
    List<BookingResponse> findAll();
    Optional<BookingResponse> findById(Long id);

    List<BookingResponse> findByPassengerId(Long id);
    List<BookingResponse> findByDriverId(Long id);
}

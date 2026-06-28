package com.example.tripnode.service.implementation;

import com.example.tripnode.dto.BookingRequest;
import com.example.tripnode.dto.BookingResponse;
import com.example.tripnode.entity.Booking;
import com.example.tripnode.service.booking.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Override
    public List<BookingResponse> findAll() {
        return List.of();
    }

    @Override
    public Optional<BookingResponse> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<BookingResponse> findByPassengerId(Long id) {
        return List.of();
    }

    @Override
    public List<BookingResponse> findByDriverId(Long id) {
        return List.of();
    }

    @Override
    public BookingResponse create(BookingRequest request) {
        return null;
    }

    @Override
    public BookingResponse update(Long id, BookingRequest request) {
        return null;
    }

    @Override
    public BookingResponse updateStatus(Long id, Booking.BookingStatus status) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

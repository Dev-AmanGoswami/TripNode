package com.example.tripnode.service.booking;

import com.example.tripnode.dto.BookingRequest;
import com.example.tripnode.dto.BookingResponse;
import com.example.tripnode.entity.Booking;

public interface BookingWriteService {
    BookingResponse create(BookingRequest request);
    BookingResponse update(Long id, BookingRequest request);
    BookingResponse updateStatus(Long id, Booking.BookingStatus status);
    Boolean acceptRide(Long id, Long driverId);
    void deleteById(Long id);
}

package com.example.tripnode.service.implementation;

import com.example.tripnode.dto.BookingRequest;
import com.example.tripnode.dto.BookingResponse;
import com.example.tripnode.dto.DriverLocationDto;
import com.example.tripnode.entity.Booking;
import com.example.tripnode.entity.Passenger;
import com.example.tripnode.mapper.BookingMapper;
import com.example.tripnode.repository.BookingRepository;
import com.example.tripnode.repository.PassengerRepository;
import com.example.tripnode.service.booking.BookingService;
import com.example.tripnode.service.location.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final PassengerRepository passengerRepository;
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final LocationService locationService;

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
        Passenger passenger = passengerRepository.findById(request.getPassengerId())
                .orElseThrow(() -> new IllegalArgumentException("Passenger not found with id: " + request.getPassengerId()));

        Booking newBooking = Booking.builder()
                .passenger(passenger)
                .pickupLocationLatitude(request.getPickUpLocationLatitude())
                .pickupLocationLongitude(request.getPickUpLocationLongitude())
                .status(Booking.BookingStatus.PENDING)
                .build();

        List<DriverLocationDto> nearbyDrivers = locationService.getNearbyDrivers(
            request.getPickUpLocationLatitude(),
            request.getPickUpLocationLongitude(),
            10.0
        );


        return bookingMapper.toResponse(newBooking);
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

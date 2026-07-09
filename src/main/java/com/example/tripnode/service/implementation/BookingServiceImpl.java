package com.example.tripnode.service.implementation;

import com.example.tripnode.dto.BookingRequest;
import com.example.tripnode.dto.BookingResponse;
import com.example.tripnode.dto.DriverLocationDto;
import com.example.tripnode.entity.Booking;
import com.example.tripnode.entity.Driver;
import com.example.tripnode.entity.Passenger;
import com.example.tripnode.mapper.BookingMapper;
import com.example.tripnode.repository.BookingRepository;
import com.example.tripnode.repository.DriverRepository;
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
    private final DriverRepository driverRepository;

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

        Driver driver = null;
        Booking.BookingStatus status = Booking.BookingStatus.PENDING;

        if(request.getDriverId() != null){
            driver = driverRepository.findById(request.getDriverId())
                    .orElseThrow(() -> new IllegalArgumentException("Driver not found with id: " + request.getDriverId()));
            if(!driver.getIsAvailable()){
                throw new IllegalStateException("Driver with id: " + request.getDriverId() + "is not available");
            }

            driver.setIsAvailable(false);
            driverRepository.save(driver);
            status = Booking.BookingStatus.CONFIRMED;
        }

        Double pickUpLocationLatitude = request.getPickUpLocationLatitude();
        Double pickUpLocationLongitude = request.getPickUpLocationLongitude();

        if(pickUpLocationLatitude == null || pickUpLocationLongitude == null){
            throw new IllegalArgumentException("Pickup location latitude and longitude are required");
        }

        Booking newBooking = Booking.builder()
                .passenger(passenger)
                .driver(driver)
                .pickupLocationLatitude(request.getPickUpLocationLatitude())
                .pickupLocationLongitude(request.getPickUpLocationLongitude())
                .dropOffLocation(request.getDropOffLocation())
                .fare(request.getFare())
                .status(Booking.BookingStatus.PENDING)
                .scheduledPickupTime(request.getScheduledPickUpTime())
                .build();

        Booking savedBooking = bookingRepository.save(newBooking);

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

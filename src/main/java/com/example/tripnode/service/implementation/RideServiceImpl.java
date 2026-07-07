package com.example.tripnode.service.implementation;

import com.example.tripnode.RideAcceptanceRequest;
import com.example.tripnode.RideAcceptanceResponse;
import com.example.tripnode.RideServiceGrpc;
import com.example.tripnode.service.booking.BookingService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideServiceImpl extends RideServiceGrpc.RideServiceImplBase {
    private final BookingService bookingService;

    @Override
    public void acceptRide(RideAcceptanceRequest request, StreamObserver<RideAcceptanceResponse> responseStreamObserver){
        //Call the booking service to update the ride with new driverId
    }
}

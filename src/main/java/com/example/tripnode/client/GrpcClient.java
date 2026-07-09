package com.example.tripnode.client;

import com.example.tripnode.RideNotificationRequest;
import com.example.tripnode.RideNotificationResponse;
import com.example.tripnode.RideNotificationServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrpcClient {
    @Value("${grpc.server.port:9091}")
    private int grpcServerPort;

    @Value("${grpc.server.host:localhost}")
    private String grpcServerHost;

    private ManagedChannel channel;
    private RideNotificationServiceGrpc.RideNotificationServiceBlockingStub rideNotificationServiceStub;


    @PostConstruct
    public void init(){
        channel = ManagedChannelBuilder.forAddress(grpcServerHost, grpcServerPort)
                .usePlaintext()
                .build();

        rideNotificationServiceStub = RideNotificationServiceGrpc.newBlockingStub(channel);
    }

    public boolean notifyDriversForNewRide(Double pickUpLocationLatitude, Double pickUpLocationLongitude, Long bookingId, List<Long> driverIds){
        RideNotificationRequest request = RideNotificationRequest.newBuilder()
                .setPickUpLocationLatitude(pickUpLocationLatitude)
                .setPickUpLocationLongitude(pickUpLocationLongitude)
                .setBookingId(bookingId)
                .addAllDriverIds(driverIds)
                .build();

        RideNotificationResponse response = rideNotificationServiceStub.notifyDriversForNewRide(request);
        return response.getSuccess();
    }

}

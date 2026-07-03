package com.example.tripnode.mapper;

import com.example.tripnode.dto.BookingResponse;
import com.example.tripnode.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public BookingResponse toResponse(Booking booking){
        return BookingResponse.builder()

                .build();
    }
}

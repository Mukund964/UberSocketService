package org.example.socketstarter.dtos;

import com.example.EntityService.Models.BookingStatus;
import com.example.EntityService.Models.Driver;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class updateBookingResponseDto {
    private Long bookingId;
    private BookingStatus status;
    private Optional<Driver> driver;
}

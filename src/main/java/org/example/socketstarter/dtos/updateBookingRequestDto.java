package org.example.socketstarter.dtos;

import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class updateBookingRequestDto {
    private String BookingStatus;
    private Optional<Long> driverId;
}

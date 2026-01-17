package org.example.socketstarter.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RideResponseDto {
    public Boolean response;
    public Long bookingId;
}

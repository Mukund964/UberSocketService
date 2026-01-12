package org.example.socketstarter.dtos;

import com.example.EntityService.Models.ExactLocation;

import java.util.List;

public class RideRequestDto {
    private Long PassengerId;
    private Integer BookingId;
    private ExactLocation StartLocation;
    private ExactLocation EndLocation;
    private List<Long> DriverId;
}

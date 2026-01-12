package org.example.socketstarter.Controllers;

import org.example.socketstarter.dtos.RideRequestDto;
import org.example.socketstarter.dtos.RideResponseDto;
import org.example.socketstarter.dtos.updateBookingRequestDto;
import org.example.socketstarter.dtos.updateBookingResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/socket")
public class RideRequestController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RestTemplate restTemplate;

    RideRequestController(SimpMessagingTemplate simpMessagingTemplate,RestTemplate restTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.restTemplate = restTemplate;
    }
    @PostMapping("/newride")
    public ResponseEntity<Boolean> raiseRideRequest(@RequestBody RideRequestDto rideRequestDto){
        System.out.println("Ride Request Received");
        sendDriversNewRideRequest(rideRequestDto);
        System.out.println("Req completed");
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    public void  sendDriversNewRideRequest(RideRequestDto rideRequestDto){
        simpMessagingTemplate.convertAndSend("/topic/rideRequest", rideRequestDto);
    }

    @MessageMapping("/rideresponse/{userId}")
    public void rideResponseHandler(@DestinationVariable String userId, RideResponseDto rideResponseDto){
        updateBookingRequestDto requestDto = updateBookingRequestDto.builder()
                .driverId(Optional.of(Long.parseLong(userId)))
                .status("SCHEDULED")
                .build();
        ResponseEntity<updateBookingResponseDto> result = this.restTemplate.postForEntity("http://localhost:8001/api/v1/booking/" + rideResponseDto.bookingId, requestDto, updateBookingResponseDto.class);
        System.out.println(result.getStatusCode());
    }
}

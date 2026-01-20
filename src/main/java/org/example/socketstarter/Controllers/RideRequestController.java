package org.example.socketstarter.Controllers;

import org.example.socketstarter.dtos.RideRequestDto;
import org.example.socketstarter.dtos.RideResponseDto;
import org.example.socketstarter.dtos.updateBookingRequestDto;
import org.example.socketstarter.dtos.updateBookingResponseDto;
import org.example.socketstarter.producer.kafkaProducerService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
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
    private final kafkaProducerService kafkaProducerService;

    RideRequestController(SimpMessagingTemplate simpMessagingTemplate,RestTemplate restTemplate,kafkaProducerService kafkaProducerService){
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.restTemplate = restTemplate;
        this.kafkaProducerService = kafkaProducerService;
    }
    @PostMapping("/newride")
    public ResponseEntity<Boolean> raiseRideRequest(@RequestBody RideRequestDto rideRequestDto){
        sendDriversNewRideRequest(rideRequestDto);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    public void  sendDriversNewRideRequest(RideRequestDto rideRequestDto){
        simpMessagingTemplate.convertAndSend("/topic/rideRequest", rideRequestDto);
    }

    @MessageMapping("/rideresponse/{userId}")
    public synchronized void rideResponseHandler(@DestinationVariable Long userId, @Payload RideResponseDto rideResponseDto){

        kafkaProducerService.publishMessage("sample-topic" , "Hello");

        updateBookingRequestDto requestDto = updateBookingRequestDto.builder()
                .driverId(Optional.of(userId))
                .BookingStatus("SCHEDULED")
                .build();
       // this.restTemplate.patchForObject("http://localhost:7777/api/v1/booking/update/" + rideResponseDto.bookingId, requestDto, updateBookingResponseDto.class);
      
    }
}

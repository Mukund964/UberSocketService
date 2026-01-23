package org.example.socketstarter.producer;

import org.example.socketstarter.dtos.updateBookingRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaProducerService {

    @Autowired
    private KafkaTemplate<String, updateBookingRequestDto> kafkaTemplate;


    public void publishMessage(String topic, updateBookingRequestDto bookingRequestDto){
        kafkaTemplate.send(topic,bookingRequestDto);
    }
}

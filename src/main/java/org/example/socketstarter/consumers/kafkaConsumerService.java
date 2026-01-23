package org.example.socketstarter.consumers;

import org.example.socketstarter.dtos.updateBookingRequestDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumerService {

    @KafkaListener(topics = "sample-topic-1", groupId = "sample-group")
    public void listen(updateBookingRequestDto requestdto) {
        System.out.println("Received Message in service : " + requestdto);
    }
}

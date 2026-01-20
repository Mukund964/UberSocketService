package org.example.socketstarter.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumerService1 {
    @KafkaListener(topics = "sample-topic", groupId = "sample-group-1")
    public void listen(String message) {
        System.out.println("Received Message service1: " + message);
    }
}

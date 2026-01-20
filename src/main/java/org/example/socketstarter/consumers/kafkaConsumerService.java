package org.example.socketstarter.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumerService {

    @KafkaListener(topics = "sample-topic", groupId = "foo")
    public void listen(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}

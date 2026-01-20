package org.example.socketstarter.consumers;

import org.springframework.kafka.annotation.KafkaListener;

public class kafkaConsumerService1 {
    @KafkaListener(topics = "sample-topic", groupId = "foo")
    public void listen(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}

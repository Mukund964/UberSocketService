package org.example.socketstarter.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void publishMessage(String topic, String msg){
        kafkaTemplate.send(topic,msg);
    }
}

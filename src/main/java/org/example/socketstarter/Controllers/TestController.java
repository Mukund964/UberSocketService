package org.example.socketstarter.Controllers;

import org.example.socketstarter.dtos.TestRequest;
import org.example.socketstarter.dtos.TestResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public TestController(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public TestResponse PingCheck(TestRequest testRequest){
        System.out.println(testRequest.getData());
        return TestResponse.builder().Data(testRequest.getData()).build();
    }

//    @Scheduled(fixedDelay = 2000)
//    public void PeriodicMessage(){
//        System.out.println("Executed periodic function");
//      simpMessagingTemplate.convertAndSend("/topic/scheduled", "Periodic Message sent " + System.currentTimeMillis());
//    }
}

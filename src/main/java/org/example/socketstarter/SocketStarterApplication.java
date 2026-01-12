package org.example.socketstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SocketStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketStarterApplication.class, args);
    }

}
